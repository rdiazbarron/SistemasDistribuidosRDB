from django.db import models

# Create your models here.
class Participante(models.Model):
    carnet = models.CharField(max_length=10)
    nombre = models.CharField(max_length=100)
    apellido = models.CharField(max_length=100)
    fecha_nacimiento = models.DateField()
    carrera = models.CharField(max_length=100)
    ciudad = models.CharField(max_length=100)
    direccion = models.CharField(max_length=100)
    correo_electronico = models.EmailField()
    telefono = models.CharField(max_length=10)

    def __str__(self):
        return self.nombre + " " + self.apellido