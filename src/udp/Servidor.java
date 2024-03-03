package udp;

// Esta clase nos sirve para recibir y enviar informacion o datos

import java.net.DatagramPacket;

// Esta clase es el socket de conexion
import java.net.DatagramSocket;

import java.net.InetAddress;
import java.io.IOException;


public class Servidor {
    public static void main(String[] args) {

        int numero, cuadrado;
        String mensajeRespuesta;

        DatagramPacket paqueteEntrada, paqueteSalida;

        // Creamos el socket de conexion
        DatagramSocket conexionCliente;

        int puerto = 5432;
        String direccionIp = "127.0.20.1";

        try {
            while (true) {
                // creamos el servidor
                conexionCliente = new DatagramSocket(puerto);
                System.out.println("Servidor escuchando en el puerto " + puerto + " y la direccion ip: " + InetAddress.getLocalHost());

                // Creamos un buffer para almacenar los datos
                byte[] buffer = new byte[1024];

                // Creamos un paquete para recibir los datos
                paqueteEntrada = new DatagramPacket(buffer, buffer.length);

                // Esperamos la conexion de algun cliente
                conexionCliente.receive(paqueteEntrada);

                String numeroString = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println("Datos recibidos del cliente: " + numeroString);

                numero = Integer.parseInt(numeroString);
                cuadrado = numero * numero;
                mensajeRespuesta = String.valueOf(cuadrado);

                paqueteSalida = new DatagramPacket(mensajeRespuesta.getBytes(), mensajeRespuesta.length(), paqueteEntrada.getAddress(), paqueteEntrada.getPort());

                conexionCliente.send(paqueteSalida);
                conexionCliente.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
