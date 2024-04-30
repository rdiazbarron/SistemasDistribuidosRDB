# from celery import shared_task
# from django.conf import settings
# from django.core.mail import send_mail, EmailMessage
# from django.core.mail.backends.smtp import EmailBackend
# import logging

# Configura el logger de Django para usarlo en tu tarea.
# logger = logging.getLogger(__name__)  # Cambiado a __name__ para ser más específico

# @shared_task
# def send_welcome_email(email, name):
#     logger.info('Iniciando tarea send_welcome_email')
#     subject = 'Bienvenido/a a CCBOL'
#     message = f'Hola {name}, gracias por registrarte en CCBOL.'
#     try:
#         logger.info('Intentando conectar al servidor de email')
#         with EmailBackend(
#             host=settings.EMAIL_HOST,
#             port=settings.EMAIL_PORT,
#             username=settings.EMAIL_HOST_USER,
#             password=settings.EMAIL_HOST_PASSWORD,
#             use_tls=settings.EMAIL_USE_TLS
#         ) as backend:
#             logger.info('Conexión establecida, enviando email')
#             backend.send_messages([
#                 backend.create_email_message(subject, message, settings.EMAIL_HOST_USER, [email])
#             ])
#             logger.info('Correo electrónico enviado exitosamente')
#     except Exception as e:
#         logger.error('Error al enviar el correo', exc_info=True)
#         raise
# import sendgrid
# from sendgrid.helpers.mail import Mail
# from django.conf import settings

# def send_welcome_email(email, name):
#     sg = sendgrid.SendGridAPIClient(api_key=settings.SENDGRID_API_KEY)
#     from_email = 'diazbarronricardojairo@gmail.com'  # El email que tienes verificado con SendGrid
#     to_email = email
#     subject = 'Bienvenido/a a CCBOL'
#     content = f'Hola {name}, gracias por registrarte en CCBOL.'
    
#     # Crear el objeto de correo electrónico con SendGrid
#     mail = Mail(
#         from_email=from_email,
#         to_emails=to_email,
#         subject=subject,
#         plain_text_content=content
#     )
    
#     try:
#         # Enviar el correo electrónico
#         response = sg.send(mail)
#         print(f"Status code: {response.status_code}")
#         print(f"Body: {response.body}")
#         print(f"Headers: {response.headers}")
#     except Exception as e:
#         # Manejar la excepción
#         print(f"Error: {e}")