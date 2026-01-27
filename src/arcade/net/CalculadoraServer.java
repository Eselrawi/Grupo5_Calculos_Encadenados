package src.arcade.net;

import src.arcade.logic.CalculadoraGame;
import java.io.*;
import java.net.*;

public class CalculadoraServer {
    public static void main(String[] args) {
        int puerto = 5000; // Puerto por defecto para el Arcade
        try (ServerSocket server = new ServerSocket(puerto)) {
            System.out.println("--- SERVIDOR CÁLCULOS ENCADENADOS LISTO ---");
            System.out.println("Esperando jugadores en el puerto " + puerto + "...");

            while (true) {
                Socket cliente = server.accept();
                System.out.println("Nuevo jugador conectado: " + cliente.getInetAddress());
                
                // Creamos un hilo para que cada jugador tenga su partida propia
                new Thread(new CalculadoraHandler(cliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CalculadoraHandler implements Runnable {
    private Socket socket;
    private CalculadoraGame juego;

    public CalculadoraHandler(Socket socket) {
        this.socket = socket;
        this.juego = new CalculadoraGame(); // Su propia instancia de la lógica
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println("CALCENC LISTO");

            String linea;
            while ((linea = in.readLine()) != null) {
                String[] partes = linea.split(" ");
                String cmd = partes[0].toUpperCase();

                if (cmd.equals("SALIR")) {
                    out.println("ADIOS");
                    break;
                }

                switch (cmd) {
                    case "NUEVA":
                        out.println(juego.generarSiguienteOperacion());
                        break;
                    case "RESPUESTA":
                        if (partes.length < 2) {
                            out.println("ERROR FALTA_NUMERO");
                        } else {
                            int resVal = Integer.parseInt(partes[1]);
                            if (juego.validarRespuesta(resVal)) {
                                out.println("OK RESULTADO " + juego.getResultadoActual());
                            } else {
                                out.println("ERROR REINTENTA");
                            }
                        }
                        break;
                    case "RESET":
                        juego.reset();
                        out.println("RESET OK");
                        break;
                    default:
                        out.println("ERROR COMANDO_DESCONOCIDO");
                }
            }
        } catch (Exception e) {
            System.err.println("Un jugador se ha desconectado.");
        }
    }
}
