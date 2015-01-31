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
 * �?rea de una circunferencia.
 * Escribe un programa en java que cree una comunicación mediante Sockets TCP,
 * entre un servidor y más de un cliente, para que, 
 * cuando un cliente le envíe el radio de una circunferencia al servidor, 
 * éste le envíe él área y el perímetro de la circunferencia 
 * que los mostrará por consola. 
 */
public class Ejercicio6_1 {
    
    public static void main(String[] args) {
        
        Socket cliente;
        DataInputStream entrada;
        DataOutputStream salida;
        String respuesta, mensaje;
        
        
        
        try {
            //Creamos el socket para conectarnos al puerto 5000 del servidor
            cliente = new Socket(InetAddress.getLocalHost(), 5000);
            
            entrada = new DataInputStream(cliente.getInputStream());
//Creamos los canales de entrada/salida
            salida = new DataOutputStream(cliente.getOutputStream());
            
            
// Enviamos un mensaje al servidor para saber que se ha conectado
            Double radioCircunf = 8.0;
            mensaje = "El radio de la circunferencia es";
            salida.writeDouble(radioCircunf);
            salida.writeUTF(mensaje);          
            
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
