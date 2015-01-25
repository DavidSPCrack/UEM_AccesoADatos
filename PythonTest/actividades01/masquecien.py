'''
masquecien.py

Created on 17/1/2015

@author: david.sancho
'''

a = int(input("Por favor introduzca un numero: "))
b = int(input("Por favor introduzca otro numero: "))

resultado = a + b

print ("La suma de", a, "y", b, "es", resultado)
if(resultado > 100):
    print("El resultado supera la centena")