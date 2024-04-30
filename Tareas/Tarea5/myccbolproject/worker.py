import os
import django
import pika
import json
from django.core.mail import send_mail
# Configurar Django
os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'myccbolproject.settings')
django.setup()

def enviar_email(nombre, correo):
    send_mail(
        subject='Bienvenido/a a nuestro sitio',
        message=f'Hola {nombre}, gracias por registrarte.',
        from_email='diazbarronricardojairo2@gmail.com',  # Asegúrate de reemplazar esto con tu correo configurado
        recipient_list=[correo],
        fail_silently=False,
    )




def callback(ch, method, properties, body):
    data = json.loads(body)
    enviar_email(data['nombre'], data['correo'])

connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

channel.queue_declare(queue='email_queue')
channel.basic_consume(queue='email_queue', on_message_callback=callback, auto_ack=True)

print('Worker iniciado. Esperando mensajes...')
channel.start_consuming()

