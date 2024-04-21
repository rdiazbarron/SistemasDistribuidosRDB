from django.shortcuts import render, redirect
from .forms import RegistroParticipanteForm
from django.http import HttpResponse
from .tasks import send_welcome_email
def index(request):
    return HttpResponse("Hello, world. You're at the inscriptions index.")


def registro_participante(request):
    if request.method == 'POST':
        form = RegistroParticipanteForm(request.POST)
        if form.is_valid():
            participante = form.save()
            send_welcome_email.delay(participante.correo_electronico, participante.nombre)
            return redirect('/')
    else:
        form = RegistroParticipanteForm()
    return render(request, 'registro_participante.html', {'form': form})

