from django.urls import path
from .views import RegistroParticipanteView

from . import views

urlpatterns = [
    
    path('registro/', RegistroParticipanteView.as_view(), name='registro_participante'),
]