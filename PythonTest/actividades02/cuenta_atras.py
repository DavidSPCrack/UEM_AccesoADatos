'''
cuenta_atras.py

Created on 24/1/2015

@author: david.sancho
'''

def catras(n):
    print (n)
    if(n > 0):
        catras(n-1)
    
num = int(input("Introduzca un numero: "))
catras(num)