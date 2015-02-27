'''
Created on 21/2/2015

@author: david.sancho, samuel.garcia, luis.salgado
'''


import psycopg2                             
import psycopg2.extras
import sys
import traceback
import pprint 

# Para la conexion a la base de datos en Postgresql
# Para mostrar los valores de las tuplas recibidas

print('')
print("BIENVENIDOS A PYTHON Y A LA BASE DE DATOS POSTGRESQL")
print('')
conx = None
# Para destruir cualquier conexion conx existente
print("Conexion a la Base de Datos Postgresql")

# Se usa try para poder capturar las excepciones producidas durante la conexion
try:
    conx = psycopg2.connect("dbname=gDevelopers user=uem_admin password=admin host=127.0.0.1 port=5432")
    # Ver la version de postgresql
    print("Estableciendo conexion a la base de datos ...")
    # conx.cursor devuelve un objeto cursor necesario para realizar las consultas SQL
    cur = conx.cursor()
    print ("Conectado!\n")
    # Mostrar la version de Postgresql
    cur.execute('select version()')
    version = cur.fetchone()
    print ("version\n")   
   
   
    # A continuacion, se ejecutan una serie de consultas o queries
    # Si existe la tabla Personas se borrara para evitar excepciones al ejecutar el codigo de nuevo
    cur.execute("DROP TABLE IF EXISTS Persona")
    print("La tabla Persona se ha eliminado")    
    # Se crea una tabla nueva llamada Persona con un campo que sera clave primaria
    cur.execute("CREATE TABLE Persona  (id_persona serial PRIMARY KEY, nombre varchar, apellidos varchar, direccion varchar, dni varchar, telefono integer, fechanaci date)")
    # Metemos valores en la tabla
    cur.execute("INSERT INTO Persona (nombre, apellidos, direccion, dni, telefono, fechanaci) VALUES (%s, %s, %s, %s, %s, %s)",("Miguel", "Sanchez","calle falsa 1", "50123456A" ,911234567,"25-01-1980"))
    cur.execute("INSERT INTO Persona (nombre, apellidos, direccion, dni, telefono, fechanaci) VALUES (%s, %s, %s, %s, %s, %s)",("Luis", "Salgado","calle falsa 2", "08365147S" ,911234567,"14-06-1990"))
    cur.execute("INSERT INTO Persona (nombre, apellidos, direccion, dni, telefono, fechanaci) VALUES (%s, %s, %s, %s, %s, %s)",("David", "Sancho","calle falsa 3", "11111111B" ,911234567,"02-07-1990"))
    
    
    # Si existe la tabla Usuario se borrara para evitar excepciones al ejecutar el codigo de nuevo
    cur.execute("DROP TABLE IF EXISTS Usuario")
    print("La tabla Usuario se ha eliminado")
    # Se crea una tabla nueva llamada Usuario con un campo que sera clave primaria
    cur.execute("CREATE TABLE Usuario  (id_usuario serial PRIMARY KEY, username varchar, email varchar, password varchar, id_persona integer)")
    # Metemos valores en la tabla
    cur.execute("INSERT INTO Usuario (username, email, password, id_persona) VALUES (%s, %s, %s, %s)",("Miguel","miguel@hotmail.com", "12345",1))
    cur.execute("INSERT INTO Usuario (username, email, password, id_persona) VALUES (%s, %s, %s, %s)",("Luis","luisl@hotmail.com", "67890",2))
    cur.execute("INSERT INTO Usuario (username, email, password, id_persona) VALUES (%s, %s, %s, %s)",("David","davidl@hotmail.com", "abcde",3))
    
    
    # Si existe la tabla Beneficios se borrara para evitar excepciones al ejecutar el codigo de nuevo
    cur.execute("DROP TABLE IF EXISTS Beneficios")
    print("La tabla Beneficios se ha eliminado")
    # Se crea una tabla nueva llamada Beneficios con un campo que sera clave primaria
    cur.execute("CREATE TABLE Beneficios (id_beneficio serial PRIMARY KEY, tipobeneficio varchar, valorbeneficio integer,id_usuario integer, id_persona integer)")
    # Metemos valores en la tabla
    cur.execute("INSERT INTO Beneficios (tipobeneficio, valorbeneficio, id_usuario) VALUES (%s, %s, %s)",("Compra",1500, 1))
    cur.execute("INSERT INTO Beneficios (tipobeneficio, valorbeneficio, id_usuario) VALUES (%s, %s, %s)",("Venta",2500, 2))
    cur.execute("INSERT INTO Beneficios (tipobeneficio, valorbeneficio, id_usuario) VALUES (%s, %s, %s)",("Compra",2500, 3))
    
    
    # Si existe la tabla transacciones se borrara para evitar excepciones al ejecutar el codigo de nuevo
    cur.execute("DROP TABLE IF EXISTS Transacciones")
    print("La tabla Transacciones se ha eliminado")
    # Se crea una tabla nueva llamada Transaccioness con un campo que sera clave primaria
    cur.execute("CREATE TABLE Transacciones (id_transaccion serial PRIMARY KEY, fechatran date, preciototal integer,id_datoenvio integer, id_mediopago integer, id_usuario integer, id_persona integer)")
    # Metemos valores en la tabla
    cur.execute("INSERT INTO Transacciones (fechatran, preciototal, id_datoenvio, id_mediopago, id_usuario) VALUES (%s, %s, %s, %s, %s)",("24-02-2015",1500, 1,1,1))
    cur.execute("INSERT INTO Transacciones (fechatran, preciototal, id_datoenvio, id_mediopago, id_usuario) VALUES (%s, %s, %s, %s, %s)",("12-02-2015",1500, 2,2,2))
    cur.execute("INSERT INTO Transacciones (fechatran, preciototal, id_datoenvio, id_mediopago, id_usuario) VALUES (%s, %s, %s, %s, %s)",("24-01-2015",1500, 3,3,3))
    
    
    # Si existe la tabla Mediopago se borrara para evitar excepciones al ejecutar el codigo de nuevo
    cur.execute("DROP TABLE IF EXISTS Mediopago")
    print("La tabla Mediopago se ha eliminado")
    # Se crea una tabla nueva llamada Transaccioness con un campo que sera clave primaria
    cur.execute("CREATE TABLE Mediopago (id_mediopago serial PRIMARY KEY, tipompag varchar, valormpag varchar)")
    # Metemos valores en la tabla
    cur.execute("INSERT INTO Mediopago (tipompag, valormpag) VALUES (%s, %s)",("tarjeta",1500))
    cur.execute("INSERT INTO Mediopago (tipompag, valormpag) VALUES (%s, %s)",("efectivo",2500))
    cur.execute("INSERT INTO Mediopago (tipompag, valormpag) VALUES (%s, %s)",("cheque",2500))
    
    
    # Si existe la tabla Datosenvio se borrara para evitar excepciones al ejecutar el codigo de nuevo
    cur.execute("DROP TABLE IF EXISTS Datosenvio")
    print("La tabla Datosenvio se ha eliminado")
    # Se crea una tabla nueva llamada Transaccioness con un campo que sera clave primaria
    cur.execute("CREATE TABLE Datosenvio (id_datosenvio serial PRIMARY KEY, direccion varchar)")
    # Metemos valores en la tabla
    cur.execute("INSERT INTO Datosenvio (direccion) VALUES ('%s')" % "calle falsa 1")
    cur.execute("INSERT INTO Datosenvio (direccion) VALUES ('%s')" % "calle falsa 2")
    cur.execute("INSERT INTO Datosenvio (direccion) VALUES ('%s')" % "calle falsa 3")
   

    # Si existe la tabla Detalletransaccion se borrara para evitar excepciones al ejecutar el codigo de nuevo
    cur.execute("DROP TABLE IF EXISTS Detalletransaccion")
    print("La tabla Detalletransaccion se ha eliminado")
    # Se crea una tabla nueva llamada Producto con un campo que sera clave primaria
    cur.execute("CREATE TABLE Detalletransaccion (id_Detalletransaccion serial PRIMARY KEY, unidades integer, precio_unidad integer, iva integer, descuento integer, bonificacion integer, id_producto integer, id_servicio integer, id_transaccion integer)")
    # Metemos valores en la tabla
    cur.execute("INSERT INTO Detalletransaccion (unidades, precio_unidad, iva, descuento, bonificacion, id_producto, id_servicio, id_transaccion) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)",(1500, 1, 21, 21,0,1,1,1))
    cur.execute("INSERT INTO Detalletransaccion (unidades, precio_unidad, iva, descuento, bonificacion, id_producto, id_servicio, id_transaccion) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)",(2500, 1, 21, 21,0,2,2,2))
    cur.execute("INSERT INTO Detalletransaccion (unidades, precio_unidad, iva, descuento, bonificacion, id_producto, id_servicio, id_transaccion) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)",(2500, 1, 21, 21,0,3,3,3))

    # Si existe la tabla Producto se borrara para evitar excepciones al ejecutar el codigo de nuevo
    cur.execute("DROP TABLE IF EXISTS Producto")
    print("La tabla Producto se ha eliminado")
    # Se crea una tabla nueva llamada Producto con un campo que sera clave primaria
    cur.execute("CREATE TABLE Producto (id_producto serial PRIMARY KEY, precio integer, nombre varchar)")
    # Metemos valores en la tabla
    cur.execute("INSERT INTO Producto (precio, nombre) VALUES (%s, %s)",(1500,"chicles"))
    cur.execute("INSERT INTO Producto (precio, nombre) VALUES (%s, %s)",(2500,"pitos"))
    cur.execute("INSERT INTO Producto (precio, nombre) VALUES (%s, %s)",(2500,"flautas"))  
          
    
    # Si existe la tabla Servicio se borrara para evitar excepciones al ejecutar el codigo de nuevo
    cur.execute("DROP TABLE IF EXISTS Servicio")
    print("La tabla Servicio se ha eliminado")
    # Se crea una tabla nueva llamada Servicio con un campo que sera clave primaria
    cur.execute("CREATE TABLE Servicio (id_servicio serial PRIMARY KEY, tarifa integer, nombre varchar)")
    # Metemos valores en la tabla
    cur.execute("INSERT INTO Servicio (tarifa, nombre) VALUES (%s, %s)",(1500,"Venta producto"))
    cur.execute("INSERT INTO Servicio (tarifa, nombre) VALUES (%s, %s)",(2500,"Venta producto"))
    cur.execute("INSERT INTO Servicio (tarifa, nombre) VALUES (%s, %s)",(2500,"Venta producto"))

    # Hacemos las Foreign key
    cur.execute("ALTER TABLE Usuario ADD CONSTRAINT fk_persona FOREIGN KEY (id_persona) REFERENCES Persona (id_persona)")
    cur.execute("ALTER TABLE Beneficios ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario)")
    cur.execute("ALTER TABLE Transacciones ADD CONSTRAINT fk_user FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario)")
    cur.execute("ALTER TABLE Transacciones ADD CONSTRAINT fk_medpago FOREIGN KEY (id_mediopago) REFERENCES Mediopago (id_mediopago)")
    cur.execute("ALTER TABLE Transacciones ADD CONSTRAINT fk_datosenvio FOREIGN KEY (id_datoenvio) REFERENCES Datosenvio (id_datosenvio)")
    cur.execute("ALTER TABLE Detalletransaccion ADD CONSTRAINT fk_transaccion FOREIGN KEY (id_transaccion) REFERENCES Transacciones (id_transaccion)")
    cur.execute("ALTER TABLE Detalletransaccion ADD CONSTRAINT fk_producto FOREIGN KEY (id_producto) REFERENCES Producto (id_producto)")
    cur.execute("ALTER TABLE Detalletransaccion ADD CONSTRAINT fk_servicio FOREIGN KEY (id_servicio) REFERENCES Servicio (id_servicio)")

    print()
    
    # Confirmar cambios y hacerlos permanentes
    conx.commit()
    # Se cierra el cursor
    cur.close()
    # Se cierra la conexion
    conx.close()
    print ("La conexion con la base de datos se ha cerrado")
    
except:
    print ("No se puede conectar con la Base de Datos")
    traceback.print_exc()