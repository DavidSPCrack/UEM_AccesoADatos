'''
Ejercicio 06

Created on 17/1/2015

@author: david.sancho
'''

f2C = float(input("Introduzca temperatura para convertir grados Fahrenheit en grados Celsius: "));
c2F = float(input("Introduzca temperatura para convertir grados Celsius en grados Fahrenheit: "));
f2C = ((f2C-32)*5)/9
c2F = ((c2F*9)/5)+32
print("De Fahrenheit a grados Celsius", f2C)
print("De Celsius a grados Fahrenheit", c2F)