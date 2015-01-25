'''
nombre.py

Created on 24/1/2015

@author: david.sancho
'''

user = "usuario"
userInput = ""
c = 0

while c < 3 or user == userInput:
    userInput = input("Introduzca un nombre: ")
    if(user == userInput):
        print ("Nombre de usuario correcto")
        break
    else:
        c += 1
    
if(c == 3):
    print ("Numero de intentos superados")