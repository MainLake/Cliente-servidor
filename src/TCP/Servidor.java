package TCP;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.io.IOException;

public class Servidor {
    public static void main(String[] args) {
        String ipAddress = "127.0.20.1";
        int port = 5432;

        double numero;
        double cuadrado;

        if(args.length > 0 ) {
            ipAddress = args[0];
        }

        try{

            ServerSocket server = new ServerSocket(port,50, InetAddress.getByName(ipAddress));

            System.out.println("Se ha iniciado el servidor en el puerto: " + port + " y en la direccion ip: " + ipAddress);

            while(true) {
                Socket socketConexion = server.accept();
                System.out.println("Conexion recibida, se ha conectado un cliente desde: " + socketConexion.getInetAddress());

                BufferedReader mensaje = new BufferedReader(new InputStreamReader(socketConexion.getInputStream()));
                String stringNumero = mensaje.readLine();

                try{
                    numero = Double.parseDouble(stringNumero);
                    System.out.println("Numero: " + numero);

                    cuadrado = Math.pow(numero, 2);

                    PrintWriter writer = new PrintWriter(socketConexion.getOutputStream());
                    writer.println("El cuadrado de " + numero + " es: " + cuadrado);
                    writer.close();

                }catch (NumberFormatException e) {
                    System.err.println("No se pudo convertir la cadena a double: " + e);
                }



                mensaje.close();
                socketConexion.close();
            }

        }catch(IOException e) {
            e.printStackTrace();
        }

    }
}
