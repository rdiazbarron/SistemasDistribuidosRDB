package org.example;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.FileList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Collections;

public class ServerDRIVE {
    private static final String CREDENTIALS_FILE_PATH = "D:\\intellijdocs\\SistemasDistribuidos\\Tareas\\primerSocket\\src\\main\\java\\org\\example\\client_secret_879850755584-ru68c6i6jcn800es4dopagm6jctpston.apps.googleusercontent.com.json";

    public static void main(String[] args) throws IOException {

        int port = 5004;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor iniciado en el puerto " + port);

        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintStream toClient = new PrintStream(clientSocket.getOutputStream()))
            {
                System.out.println("Cliente conectado...");

                String nombreArchivo = fromClient.readLine();
                System.out.println("Buscando archivo: " + nombreArchivo);

                boolean encontrado = buscarArchivoEnDrive(nombreArchivo);
                toClient.println(encontrado ? "Archivo encontrado." : "Archivo no encontrado.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean buscarArchivoEnDrive(String nombreArchivo) throws IOException {
        Drive service = getDriveService();
        FileList result = service.files().list()
                .setQ("name = '" + nombreArchivo + "'")
                .setSpaces("drive")
                .setFields("files(id, name)")
                .execute();
        return !result.getFiles().isEmpty();
    }

    private static Drive getDriveService() throws IOException {
        InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new InputStreamReader(in));

        // Configura la URI de redirección autorizada en el objeto LocalServerReceiver.
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(5004).setCallbackPath("/Callback").build();

        // Configura el flujo de autorización con la URI de redirección.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                new NetHttpTransport(), JacksonFactory.getDefaultInstance(), clientSecrets,
                Collections.singleton(DriveScopes.DRIVE))
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .setClientId("http://localhost:5004/Callback") // Asegúrate de que esta URI coincida con la configurada en la Consola de GCP
                .build();

        // Autoriza y obtén Credential
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        // Devuelve el servicio de Drive configurado con la Credential
        return new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName("Drive API Search")
                .build();
    }
}
