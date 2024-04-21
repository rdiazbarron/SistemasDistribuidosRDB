from celery import shared_task
from django.core.mail import send_mail

@shared_task
def send_welcome_email(email, name):
    subject = 'Bienvenido/a a CCBOL'
    message = f'Hola {name}, gracias por registrarte en CCBOL.'
    send_mail(subject, message, 'your-email@example.com', [email])