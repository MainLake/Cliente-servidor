package tcp_new;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Servidor {
    public static void main(String[] args) {

        int numero, cuadrado;
        int puerto = 5432;

        DataInputStream dataInput;
        DataOutputStream dataOutput;

        try{

            // Creacion del servidor para escuchar peticiones
            ServerSocket server = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en el puerto: " + puerto + " y en la direccion ip: " + InetAddress.getLocalHost());

            while(true) {
                // Esperamos a que haya una conexion
                Socket conexionCliente = server.accept();
                System.out.println("Se ha establecido la conexion con un cliente!!, con la direccion ip: " + conexionCliente.getInetAddress());

                // Configuramos la entrada y salida de datos el socket de conexion con el servidor
                dataOutput = new DataOutputStream(conexionCliente.getOutputStream());
                dataInput = new DataInputStream(conexionCliente.getInputStream());

                // Leemos la informacion enviada por el cliente
                numero = dataInput.readInt();

                // Elevamos al cuadrado el numero
                cuadrado = numero * numero;

                // Enviamos los datos a el cliente
                dataOutput.writeInt(cuadrado);

                dataOutput.close();
                dataInput.close();
                conexionCliente.close();
            }


        }catch (IOException e) {
            System.out.println("A ocurrido un error en el servidor: ");
            e.printStackTrace();
        }


    }
}
