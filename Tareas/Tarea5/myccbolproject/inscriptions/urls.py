from django.urls import path
from .views import registro_participante

from . import views

urlpatterns = [
    #path("", views.index, name="index"),
    path('registro/', registro_participante, name='registro_participante'),
]