package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.io.IOException;

import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String numero;
        String respuesta;

        DatagramPacket paqueteEntrada, paqueteSalida;

        DatagramSocket conexionServidor;

        String direccionIp = "127.0.0.1";
        int puerto = 5432;

        byte[] buffer = new byte[1024];

        if(args.length > 0) {
            direccionIp = args[0];
        }

        try {

            conexionServidor = new DatagramSocket();

            System.out.print("Ingresa el numero para calcular el cuadrado: ");
            numero = scanner.next();

            paqueteSalida = new DatagramPacket(numero.getBytes(), numero.length(), InetAddress.getByName(direccionIp), puerto);
            conexionServidor.send(paqueteSalida);

            paqueteEntrada = new DatagramPacket(buffer, buffer.length);
            conexionServidor.receive(paqueteEntrada);

            respuesta = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
            System.out.println("El cuadrado de " + numero + " es " + respuesta);

            conexionServidor.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
