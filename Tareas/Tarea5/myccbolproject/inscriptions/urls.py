from django.urls import path
from .views import RegistroParticipanteView

from . import views

urlpatterns = [
    #path("", views.index, name="index"),
    path('registro/', RegistroParticipanteView.as_view(), name='registro_participante'),
]