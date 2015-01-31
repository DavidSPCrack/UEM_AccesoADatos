/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Samuel
 */
public class Servidor6 {
    
     public static void main(String args[]) {
        ServerSocket servidor;
        Socket conexion;
        DataOutputStream salida;
        DataInputStream entrada;
        int num = 0;
        try {
// Creamos un ServerSocket en el puerto especificado
            servidor = new ServerSocket(5000);
            System.out.println("Servidor Arrancado correctamente");
            while (true) {
//Se espera la conexión de algún cliente
                conexion = servidor.accept();
                num++;
                System.out.println("Conexión número" + num + " desde: "+ conexion.getInetAddress().getHostName());
// Abrimos los canales de entrada y salida
                entrada = new DataInputStream(conexion.getInputStream());
                salida = new DataOutputStream(conexion.getOutputStream());
//Leemos el mensaje del cliente
                Double num1 = entrada.readDouble();             
                String mensaje = entrada.readUTF();
                
                
                System.out.println("Conexión n." + num + " mensaje:"+mensaje+" "+num1);
                
                
                
                Double area = Math.rint(Math.PI*num1*num1)/100;
                Double perim = Math.rint(2*Math.PI*num1)/100;
                
                String respuesta = "El área de la circunferencia es "+area
                        +" y el perímetro "+perim;
             
// Le respondemos al cliente
                
                salida.writeUTF(respuesta);
                
                
//Se cierra la conexión
                conexion.close();
                
            }
        } catch (IOException e) {
        }
    }
    
}
