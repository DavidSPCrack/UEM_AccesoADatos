'''
Created on 21/2/2015

@author: usuario.apellido
'''
print()

#Se crea una clase vacía

class Empleado:
    pass

# Se crea un objeto Empleado
javier = Empleado() 

# Se rellenan ciertos atributos del objeto
javier.nombre = 'Javier Jiménez'
javier.departamento = 'Laboratorio informática'
javier.sueldo = 850
print(javier.nombre)
print(javier.departamento)
print(javier.sueldo)
