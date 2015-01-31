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
 * 
 * Media de Números. Escribe un programa en java que cree una comunicación 
 * mediante Sockets TCP, entre un servidor y más de un cliente, para que, 
 * cuando un cliente le envíe 10 números al servidor, 
 * éste le envíe la media de dichos números que mostrará por consola. 
 */
public class Ejercicio5_1 {
    
    public static void main(String[] args) {
        
        Socket cliente;
        DataInputStream entrada;
        DataOutputStream salida;
        String respuesta;
        Double mensaje;
        String mensaje1;
        
        
        try {
            //Creamos el socket para conectarnos al puerto 5000 del servidor
            cliente = new Socket(InetAddress.getLocalHost(), 5000);
            
            entrada = new DataInputStream(cliente.getInputStream());
//Creamos los canales de entrada/salida
            salida = new DataOutputStream(cliente.getOutputStream());
            
            
// Enviamos un mensaje al servidor para saber que se ha conectado
            salida.writeDouble(1.0);
            salida.writeDouble(2.0);
            salida.writeDouble(3.0);
            salida.writeDouble(4.0);
            salida.writeDouble(5.0);
            salida.writeDouble(6.0);
            salida.writeDouble(7.0);
            salida.writeDouble(8.0);
            salida.writeDouble(9.0);
            salida.writeDouble(10.0);
            
            
            // Leemos la respuesta
            respuesta = entrada.readUTF();
            System.out.println("Mi mensaje: \n" +"10 números enviados");
            System.out.println("Respuesta del Servidor: " + respuesta);
            
            // Se cierra la conexión 
            cliente.close();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }
    
}
