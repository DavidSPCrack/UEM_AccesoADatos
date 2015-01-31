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
public class Ejercicio4_3_random {
    
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
            
            int num=(int)Math.floor(Math.random()*(1-(2+1))+(2+1));
            
            if(num == 1) {
                mensaje1 = "Farenheit";
            } else {
                mensaje1 = "Celsius";
            }
            
            mensaje = Math.floor(Math.random()*100);
                      
            
                         
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
