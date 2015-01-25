'''
grande_pequenyo.py

Created on 17/1/2015

@author: david.sancho
'''
import random
num = random.randint(0, 100)
nIn = -1

while(num != nIn):
    try:
        nIn = int(input("Introduce un numero entre 0 y 100: "))
        if nIn > num:
            print ("El numero es menor que el introducido")
        if nIn < num:
            print ("El numero es mayor que el introducido")
    except ValueError:  
            print ("El valor introducido no es un numero")
            
print ("Enhorabuena has acertado el numero!")