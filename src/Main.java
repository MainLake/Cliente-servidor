import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.io.Writer;
import java.net.Socket;

// Las dependencias usadas para la creacion de un servidor usando el protocolo tcp/ip y sockets son java.io y java.net
// java.net contiene las clases y metodos necesarios para manejar conexiones de varios tipos entre ellos el tcp
// Por otro lado java.io contiene las clases y metodos necesarios para manejar los datos a enviar o recibir de una conexion

public class Main {
    public static void main(String[] args) {

        try{
            // Para el servidor necesitamos una instancia de serverSocket que nos permita escuchar peticiones en un puerto especifico
            ServerSocket serverSocket = new ServerSocket(9999);

            System.out.println("Servidor iniciado en espera de conexion!!");

            while(true){

                // Ncesitamos poner a escuchar la instancia de serverSocket para captar las peticiones, para ello bloqueamos el flujo del programa con el metodo .accept() para esperar a una conexion o peticion
                Socket clienSocket = serverSocket.accept();
                // Cuando recibimos una conexion se crea una instancia de un socket que es el que nos permitira mantener la conexion con el cliente

                // Con la instancia de el socket tenemos acceso a varios metodos que nos permiten obtener informacion del cliente
                System.out.println("Cliente conectado desde " + clienSocket.getInetAddress());

                // La libreria io nos permite manejar el buffer de informacion recibida por el cliente, pero debemos usar otras clases para poder acceder a la informacion del cliente de la manera adecuada
                // clienSocket.getInputStream() obtiene los datos del flujo de entrada, se usa streams como estandar
                // new InputStreamReader nos permite convertir esa secuencia de datos a caracteres mas legibles
                BufferedReader reader = new BufferedReader(new InputStreamReader(clienSocket.getInputStream()));

                String clienMessage = reader.readLine();

                System.out.println("Mensaje del cliente: " + clienMessage);

                clienSocket.close();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}