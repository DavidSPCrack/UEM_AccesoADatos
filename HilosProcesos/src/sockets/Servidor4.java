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
public class Servidor4 {
    
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
                Double mensaje = entrada.readDouble();
                String mensaje1 = entrada.readUTF();
                
                System.out.println("Conexión n." + num + " mensaje: \n"
                        +"Temperatura:" +mensaje+" grados "+mensaje1);
                
                Double solucion;
                String respuesta ;
                
                if(mensaje1.equals("Celsius")) {
                    solucion = ((1.8)*mensaje)+32;
                    respuesta = mensaje+"º "+mensaje1+" son "+solucion+"º Farenheit";
                } else {
                    solucion = (mensaje-32)/(1.8);
                    respuesta = mensaje+"º "+mensaje1+" son "+solucion+"º Celsius";
                }

                
// Le respondemos al cliente
                
                salida.writeUTF(respuesta);
                
                
//Se cierra la conexión
                conexion.close();
                
            }
        } catch (IOException e) {
        }
    }
}
