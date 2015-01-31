/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author Samuel
 */
public class Ejercicio2_2_random {
    
    public static void main(String[] args) {
        
        Socket cliente;
        DataInputStream entrada;
        DataOutputStream salida;
        String respuesta;
        String mensaje;
        
        try {
            //Creamos el socket para conectarnos al puerto 5000 del servidor
            cliente = new Socket(InetAddress.getLocalHost(), 5000);
            
            entrada = new DataInputStream(cliente.getInputStream());
//Creamos los canales de entrada/salida
            salida = new DataOutputStream(cliente.getOutputStream());
            
            
            double num = Math.floor(Math.random()*100);
            int num1 = (int)num;
            mensaje = Integer.toString(num1);
// Enviamos un mensaje al servidor para saber que se ha conectado
            salida.writeUTF(mensaje);
            
            // Leemos la respuesta
            respuesta = entrada.readUTF();
            System.out.println("Mi mensaje: " + mensaje);
            System.out.println("Respuesta del Servidor: " + respuesta);
            
            // Se cierra la conexión 
            cliente.close();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }
    
}
