'''
password.py

Created on 24/1/2015

@author: david.sancho
'''

passwd = "123456"
passwdInput = input("Escriba la contraseña: ")

if(passwd != passwdInput):
    print ("Contraseña incorrecta")
else:
    print ("Contraseña correcta")