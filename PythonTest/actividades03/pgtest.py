# -*- coding: utf-8 -*-
'''
Created on 08/02/2015
@author: uemuser
'''
import psycopg2 #Para la conexión a la base de datos en Postgresql
import psycopg2.extras
import sys
import traceback
import pprint #Para mostrar los valores de las tuplas recibidas

print()
print("BIENVENIDOS A PYTHON Y A LA BASE DE DATOS POSTGRESQL")
print()
conx = None #Para destruir cualquier conexión conx existente
print("Conexión a la Base de Datos Postgresql")
#Se usa try para poder capturar las excepciones producidas durante la conexión
try:
    conx = psycopg2.connect("dbname=uemdb user=uem_admin password=admin host=127.0.0.1 port=5432")
    #Ver la versión de postgresql
    print("Estableciendo conexión a la base de datos ...")
    
    #conx.cursor devuelve un objeto cursor necesario para realizar las consultas SQL
    cur = conx.cursor()
    print ("Conectado!\n")
    #Mostrar la versión de Postgresql
    cur.execute('select version()')
    version = cur.fetchone()
    print ("version\n")
    #A continuación, se ejecutan una serie de consultas o queries
    #Si existe la tabla prueba1 se borrará para evitar excepciones al ejecutar el código de nuevo
    cur.execute("DROP TABLE IF EXISTS prueba1")
    print("La tabla pruebas1 se ha eliminado")
    
    #Se crea una tabla nueva llamada prueba1 con un campo que será clave primaria
    cur.execute("CREATE TABLE prueba1 (id serial PRIMARY KEY, nombre varchar, sueldo integer)")
    #Se insertan algunas tuplas en la tabla. La última se inserta de otra forma
    cur.execute("INSERT INTO prueba1 (nombre, sueldo) VALUES (%s, %s)",("Miguel Sánchez", 1500))
    cur.execute("INSERT INTO prueba1 (nombre, sueldo) VALUES (%s, %s)",("Clara Muñoz", 1550))
    cur.execute("INSERT INTO prueba1 (nombre, sueldo) VALUES (%s, %s)",("Paco López", 1580))
    cur.execute("INSERT INTO prueba1 (nombre, sueldo) VALUES (%s, %s)",("Luis Bermúdez", 1600))
    cur.execute(
    """INSERT INTO prueba1 (nombre, sueldo)
    VALUES (%s, %s);""",
    ("Lola Otero", 1700))
    
    cur.execute("SELECT * FROM prueba1")
    tuplas=cur.fetchall()
    
    print()
    print("Se muestran todas las tuplas con un bucle for")
    for row in tuplas:
        print (row)
    
    print()
    print("Se muestran las tuplas usando pprint")
    pprint.pprint(tuplas)
    
    #Se ven las tuplas una a una y se muestran los campos deseados de las mismas
    cur.execute("SELECT * FROM prueba1")
    
    print()
    print("Se muestran todos los campos de la fila:")
    
    while True:
        fila = cur.fetchone() #Sólo se coge una tupla del cursor
        if fila == None: #Si no hay más filas se sale del bucle while
            break
        print (fila[0], fila[1], fila[2]) #para la fila, se muestran los valores de los campos 0, 1 y 2
        print()
    
    print() #Se muestra la tupla que cumple una condición
    print("Se muestra sólo la tupla que cumpla la condición - nombre = Luis")
    sql = "select * from prueba1 where nombre like 'Luis%'"
    cur.execute(sql)
    fila = cur.fetchone()
    print (fila)
    
    #Confirmar cambios y hacerlos permanentes
    conx.commit()
    #Se cierra el cursor
    cur.close()
    #Se cierra la conexión
    conx.close()
    print ("La conexión con la base de datos se ha cerrado")

except:
    print ("No se puede conectar con la Base de Datos")
    traceback.print_exc()

#Fin del código