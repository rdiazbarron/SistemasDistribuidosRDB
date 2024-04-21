from django import forms
from .models import Participante

class RegistroParticipanteForm(forms.ModelForm):
    class Meta:
        model = Participante
        fields = '__all__'
        widgets = {
            'fecha_nacimiento': forms.DateInput(attrs={'type': 'date'}),
        }

    def clean_telefono(self):
        telefono = self.cleaned_data['telefono']
        if not telefono.isdigit():
            raise forms.ValidationError("El teléfono debe contener solo números.")
        return telefono

    def clean_correo_electronico(self):
        correo = self.cleaned_data['correo_electronico']
        if "@" not in correo:
            raise forms.ValidationError("Correo electrónico inválido.")
        return correo