package org.example.EjerciciosEnClases.ConsumirREST;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class Consumir {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        String baseUrl = "http://127.0.0.1:8000/api/v1/facturas/3";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(Consumir::parse)
                .join();  // Espera a que se complete la solicitud
    }

    public static void parse(String responseBody) {
        Gson gson = new Gson();
        Factura factura = gson.fromJson(responseBody, Factura.class);

        System.out.println("Datos de la factura:");
        System.out.println("ID: " + factura.id);
        System.out.println("Número: " + factura.numero);
        System.out.println("Fecha de emisión: " + factura.fecha_emision);
        System.out.println("CUF: " + factura.cuf);
        System.out.println("CUFD: " + factura.cufd);
        System.out.println("Monto total: " + factura.monto_total);
    }

    static class Factura {
        int id;
        String numero;
        String fecha_emision;
        String cuf;
        String cufd;
        double monto_total;
    }
}
