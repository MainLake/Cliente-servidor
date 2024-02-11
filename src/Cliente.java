import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try{
            Socket clientSocket = new Socket("localhost", 9999);

            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            writer.println("Mensaje del cliente");

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Recibe un mensaje del servidor.
            String messageFromServer = reader.readLine();

            // Imprime el mensaje del servidor.
            System.out.println("Mensaje del servidor: " + messageFromServer);

            // Cierra la conexión.
            clientSocket.close();
        }catch (IOException e){

            e.printStackTrace();

        }
    }
}
