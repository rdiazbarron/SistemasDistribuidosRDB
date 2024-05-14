
from django.contrib import admin
from django.urls import include, path
from django.views.generic import RedirectView

urlpatterns = [
    path('admin/', admin.site.urls),
    path("inscriptions/", include("inscriptions.urls")),
    path('', RedirectView.as_view(url='/admin/')),  # Agrega esta línea
   
]
