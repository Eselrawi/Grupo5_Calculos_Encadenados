package src.arcade.net;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class CalculadoraClient {
    public static void main(String[] args) {
        String host = "localhost"; // Cambiar por la IP del compañero para jugar en red
        int puerto = 5000;

        try (Socket socket = new Socket(host, puerto);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner teclado = new Scanner(System.in)) {

            System.out.println("SERVIDOR: " + in.readLine());

            while (true) {
                System.out.print("\nIntroduce comando (NUEVA, RESPUESTA x, RESET, SALIR): ");
                String input = teclado.nextLine();
                out.println(input);

                String respuesta = in.readLine();
                System.out.println("SERVIDOR DICE: " + respuesta);

                if (input.equalsIgnoreCase("SALIR")) break;
            }
        } catch (IOException e) {
            System.err.println("Error: No se pudo conectar al servidor.");
        }
    }
}