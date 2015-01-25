'''
Created on 20/12/2014

@author: usuario.apellido
'''

print("HOLA MUNDO")
print("AAAAAAAAAAAAAAAAAAAAAAAA")

mi_tupla = ("ARRAY LLAMADO TUPLA", 15, 2.8, "OTRO DATO", 25)

semaforo = "verde"

if semaforo == "verde":
    print("Cruzar la calle")
else:
    print("Esperar")
    
print("El niño busco la concha en la playa")

anio = 2001
while anio <= 2012:
    print("Informes del Año", str(anio))
    anio += 1
    
while True:
    nombre = input("Indique su nombre: ")
    print(nombre)
    if nombre:
        break
    
print("Ha terminado el while")
