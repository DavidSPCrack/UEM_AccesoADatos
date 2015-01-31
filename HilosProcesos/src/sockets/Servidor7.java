/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Math.sqrt;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Samuel
 */
public class Servidor7 {

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
                System.out.println("Conexión número" + num + " desde: " + conexion.getInetAddress().getHostName());
// Abrimos los canales de entrada y salida
                entrada = new DataInputStream(conexion.getInputStream());
                salida = new DataOutputStream(conexion.getOutputStream());
//Leemos el mensaje del cliente
                int num1 = entrada.readInt();

                System.out.println("Conexión n." + num + " mensaje: Nº enviado - " + num1);

                String solucion = "";
                int i = 0, j = 0, div = 0, raiz = 0;
                for (i = 1; i < 101; i++) { //ciclo para recorrer los numeros hasta el num 100
                    div = 0; //variable para contar cuantas veces es el residuo de dividir es 0
                    raiz = (int)sqrt(i);//la raiz del número a buscarle los primos
                    for (j = 1; j <= raiz; j++) { //ciclo para recorrer los numeros hasta la raiz de i (estos seran los divisores)
                        if (i % j == 0)//evalua la condicion de que el residuo de dividir i entre j es igual a cero
                        {
                            div++;// si la condicion anterior se cumple entonces entonces suma 1 a esta variable
                        }
                    }
                    if (div <= 1)//Si existe más d eun divisor, entonces es primo
                    {
                        solucion += i+", ";// imprime que cierto numero es primo
                    }
                }

                String respuesta = "Los números primos de " + num1
                        + " son " + solucion;

// Le respondemos al cliente
                salida.writeUTF(respuesta);

//Se cierra la conexión
                conexion.close();

            }
        } catch (IOException e) {
        }
    }
}
