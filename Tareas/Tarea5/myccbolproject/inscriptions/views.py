
# from django.urls import reverse_lazy
# from django.views.generic.edit import CreateView
# from .models import Participante
# from .forms import RegistroParticipanteForm

# class RegistroParticipanteView(CreateView):
#     model = Participante
#     form_class = RegistroParticipanteForm
#     success_url = reverse_lazy('registro_participante')

#     def form_valid(self, form):
#         # Lógica para guardar el participante
#         response = super().form_valid(form)
#         participante = form.save()

#         # Enviar mensaje a RabbitMQ
#         connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
#         channel = connection.channel()
#         channel.queue_declare(queue='email_queue')

#         mensaje = json.dumps({
#             'nombre': participante.nombre,
#             'correo': participante.correo_electronico
#         })
#         channel.basic_publish(exchange='',
#                               routing_key='email_queue',
#                               body=mensaje)
#         connection.close()

#         return response

from django.urls import reverse_lazy
from django.views.generic.edit import CreateView
from .models import Participante
from .forms import RegistroParticipanteForm

class RegistroParticipanteView(CreateView):
    model = Participante
    form_class = RegistroParticipanteForm
    success_url = reverse_lazy('registro_participante')