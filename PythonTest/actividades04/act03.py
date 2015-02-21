'''
Created on 21/2/2015

@author: usuario.apellido
'''
print()

class Alumno:
    def __init__(self, nombre):      # Constructor de la clase
        self.name = nombre
        self.deportes = []             # Crea una lista vacía de deportes para cada Alumno

    def add_deporte(self, deporte):     # Método para añadir deportes a la lista de cada alumno
        self.deportes.append(deporte)

a1 = Alumno('Javier')
a2 = Alumno('Paula')
a3 = Alumno('Carlos')
a1.add_deporte('fútbol')
a1.add_deporte('tenis')
a2.add_deporte('natación')
a2.add_deporte('atletismo')
a3.add_deporte('voleibol')
a3.add_deporte('tenis')
print(a1.deportes)
print(a2.deportes)
print(a3.deportes)
print(Alumno('Javier').name)
