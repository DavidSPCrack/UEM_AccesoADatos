'''
area.py

Created on 17/1/2015

@author: david.sancho
'''

base = float(input("Por favor introduzca la base del rectangulo: "))
altura = float(input("Por favor introduzca la altura del rectangulo: "))

area = base * altura
perimetro = (base * 2) + (altura *2)

print ("El area del rectangulo es", area)
print ("El perimetro del rectangulo es", perimetro)