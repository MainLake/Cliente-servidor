package TCP;

import java.net.InetAddress;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String ipAddress = "127.0.20.1";
        int port = 5432;

        int numero = 0;
        String mensajeRecibido = "";


        if(args.length > 0) {
            ipAddress = args[0];
        }

        try{

            Socket conexionSocket = new Socket(ipAddress, port);
            System.out.println("Se ha establecido la conexion con el servidor en el puerto: " + port + " y la direccion ip: " + ipAddress);

            PrintWriter writer = new PrintWriter(conexionSocket.getOutputStream(), true);
            BufferedReader mensaje = new BufferedReader(new InputStreamReader(conexionSocket.getInputStream()));
            System.out.println("Ingresa el numero para obtener le cuadrado: ");

            numero = sc.nextInt();
            writer.println(numero);

            mensajeRecibido = mensaje.readLine();
            System.out.println(mensajeRecibido);

            conexionSocket.close();

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}
