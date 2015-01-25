'''
ciudad.py

Created on 24/1/2015

@author: david.sancho
'''

ciudad = input("Introduzca una ciudad: ")

if(ciudad.lower() == "Madrid".lower()):
    print("Esta es la capital de España")
elif(ciudad.lower() == "Toledo".lower() or ciudad.lower() == "Cuenca".lower()):
    print("Esta es una ciudad manchega")
elif(ciudad.lower() == "Segovia".lower() or ciudad.lower() == "Burgos".lower()):
    print("Esta es una ciudad castellana")
else:
    print("La gente alli es muy amable y simpatica")