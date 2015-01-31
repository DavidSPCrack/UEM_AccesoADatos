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
 * Descomposición en factores primos. 
 * Escribe un programa en java que cree una comunicación mediante Sockets TCP,
 * entre un servidor y más de un cliente, para que, 
 * cuando un cliente le envíe un número N al servidor, 
 * éste le envíe todos los números primos en los que puede descomponerse 
 * el número N, que los mostrará por consola
 */
public class Ejercicio7_1 {
    
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
            
            
            int num = (int) Math.floor(Math.random()*100);
            
// Enviamos un mensaje al servidor para saber que se ha conectado
            salida.writeInt(num);
            
            // Leemos la respuesta
            respuesta = entrada.readUTF();
            System.out.println("Mi mensaje: " + num);
            System.out.println("Respuesta del Servidor: " + respuesta);
            
            // Se cierra la conexión 
            cliente.close();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }
    
}
