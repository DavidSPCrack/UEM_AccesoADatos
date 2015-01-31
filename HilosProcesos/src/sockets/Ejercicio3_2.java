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

/**
 *
 * @author Samuel
 */
public class Ejercicio3_2 {
    
    public static void main(String[] args) {
        
        Socket cliente;
        DataInputStream entrada;
        DataOutputStream salida;
        String respuesta;
        int mensaje,mensaje1;
        
        try {
            //Creamos el socket para conectarnos al puerto 5000 del servidor
            cliente = new Socket(InetAddress.getLocalHost(), 5000);
            
            entrada = new DataInputStream(cliente.getInputStream());
//Creamos los canales de entrada/salida
            salida = new DataOutputStream(cliente.getOutputStream());
            
            mensaje = 5;
            mensaje1 = 6;
// Enviamos un mensaje al servidor para saber que se ha conectado
            salida.writeInt(mensaje);
            salida.writeInt(mensaje1);
            
            // Leemos la respuesta
            respuesta = entrada.readUTF();
            System.out.println("Mi mensaje: \n" +"Número 1: " +mensaje+"\nNúmero 2: "+mensaje1);
            System.out.println("Respuesta del Servidor: " + respuesta);
            
            // Se cierra la conexión 
            cliente.close();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }
}
