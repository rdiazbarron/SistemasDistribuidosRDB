package org.example.EjerciciosEnClases.PracticaFinal;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServidorReserva extends UnicastRemoteObject implements IServidorReserva {
    protected ServidorReserva() throws RemoteException {
    }

    public double cotizar(String fechainicio, String fechafin, String fechacotizacion) throws Exception {
        double valorrecibido = cotizar2(fechacotizacion);
        return valorrecibido;
    }

    public boolean reservar(String fechainicio, String fechafin, int idcliente, String fechacompra) {
        return false;
    }

    private double cotizar2(String fechacotizacion) throws Exception {
        // Crear la conexión SOAP
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Crear el mensaje SOAP
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tem", "http://tempuri.org/");

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("cotizar", "tem");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("fechacotizacion", "tem");
        soapBodyElem1.addTextNode(fechacotizacion);

        // URL del servicio SOAP
        String url = "http://localhost:51852/WebServiceBCentral.asmx?WSDL";

        // Llamada SOAP
        SOAPMessage soapResponse = soapConnection.call(soapMessage, url);

        // Procesar la respuesta
        SOAPBody responseBody = soapResponse.getSOAPBody();
        Node cotizarResult = (Node) responseBody.getElementsByTagName("cotizarResult").item(0);
        double resultado = Double.parseDouble(cotizarResult.getTextContent());

        soapConnection.close();
        return resultado;
    }
}
