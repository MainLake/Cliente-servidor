package tcp_new;

import java.net.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.util.Scanner;

public class Cliente {


    public static void main(String[] args) {

        Scanner scaner = new Scanner(System.in);
        int numero, cuadrado;

        String direccionIp = "127.0.0.1";
        int puerto = 5432;

        DataInputStream dataInput;
        DataOutputStream dataOutput;

        if(args.length > 0) {
            direccionIp = args[0];
        }

        try {

            // Establecemos una conexion con el servidor
            Socket conexionServidor = new Socket(direccionIp, puerto);
            System.out.println("Conexion establecida con el servidor en el puerto " + puerto + " y en la direccion ip: " + direccionIp);

            // Configuramos la entrada y salida de datos con la conexion del socket
            dataInput = new DataInputStream(conexionServidor.getInputStream());
            dataOutput = new DataOutputStream(conexionServidor.getOutputStream());

            // Solicitamos el numero para enviar al servidor
            System.out.println("Ingresa el numero para calcular el cuadrado: ");
            numero = scaner.nextInt();

            // Enviamos el numero recibido al servidor
            dataOutput.writeInt(numero);

            // Recibimos la respuesta del servidor
            cuadrado = dataInput.readInt();
            System.out.println("El cuadrado de " + numero + " es: " + cuadrado);

            dataOutput.close();
            dataInput.close();
            conexionServidor.close();

        }catch (IOException e) {
           e.printStackTrace();
        }

    }

}
