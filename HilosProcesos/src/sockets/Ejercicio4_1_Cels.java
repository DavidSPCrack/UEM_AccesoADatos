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
 * Conversor Celsius-Fahrenheit y viceversa. 
 * Escribe un programa en java que cree una comunicación mediante Sockets TCP, 
 * entre un servidor y más de un cliente, para que, 
 * cuando un cliente le envíe una temperatura, Celsius o Fahrenheit, 
 * al servidor, éste le envíe la temperatura convertida a la otra unidad 
 * que mostrará por consola con un mensaje de tipo de temperatura.
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
            //Creamos el socket para conectarnos al puerto 5000 del servidor
            cliente = new Socket(InetAddress.getLocalHost(), 5000);
            
            entrada = new DataInputStream(cliente.getInputStream());
//Creamos los canales de entrada/salida
            salida = new DataOutputStream(cliente.getOutputStream());
            
            mensaje = 5.0;
            mensaje1 = "Celsius";
// Enviamos un mensaje al servidor para saber que se ha conectado
            salida.writeDouble(mensaje);
            salida.writeUTF(mensaje1);
            
            // Leemos la respuesta
            respuesta = entrada.readUTF();
            System.out.println("Mi mensaje: \n" +"Temperatura: " +mensaje+"º "+mensaje1);
            System.out.println("Respuesta del Servidor: " + respuesta);
            
            // Se cierra la conexión 
            cliente.close();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }
    
}
