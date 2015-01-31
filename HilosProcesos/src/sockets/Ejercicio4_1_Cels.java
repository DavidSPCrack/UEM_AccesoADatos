package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author David
 * 
 */
public class Ejercicio4_1_Cels {
    
    public static void main(String[] args) {
        
        Socket cliente;
        DataInputStream entrada;
        DataOutputStream salida;
        String respuesta;
        Double mensaje;
        String mensaje1;
        
        try {
            cliente = new Socket(InetAddress.getLocalHost(), 5000);
            
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
            
            mensaje = 5.0;
            mensaje1 = "Celsius";
            salida.writeDouble(mensaje);
            salida.writeUTF(mensaje1);
            
            respuesta = entrada.readUTF();
            System.out.println("Mi mensaje: \n" +"Temperatura: " +mensaje+"º "+mensaje1);
            System.out.println("Respuesta del Servidor: " + respuesta);
            
            cliente.close();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }
    
}
