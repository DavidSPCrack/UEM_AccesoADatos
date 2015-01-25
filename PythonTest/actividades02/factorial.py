'''
factorial.py

Created on 24/1/2015

@author: david.sancho
'''

def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n-1)
    
num = int(input("Introduzca un numero: "))
fact = factorial(num)

print("El factorial del numero introducido es", fact)