'''
Created on 21/2/2015

@author: usuario.apellido
'''
class Articulos:
    def __init__(self):
        self.data = []
    def add(self, x):
        self.data.append(x)
    def addtwice(self, x):
        self.add(x)
        self.add(x)


print()

#Herencia

class Palabras (Articulos):
    def __init__(self):
        self.data = []
        
    def Mayusc (self, palabra):
        self.palabra = palabra
        print(palabra.upper())

frase1 = Palabras()
frase1.Mayusc('todo está en minúsculas')
frase1.add('HOLA')
frase1.add('BUENOS DIAS')
frase1.addtwice('adios')
print(frase1.data)

print()

#Herencia Múltiple

class Adjuntar():

    def __init__(self):
        self.data = []
        
    def adjuntarTexto(self, palabra):
        palabra = palabra + 'TEXTOAÑADIDO'
        print(palabra)

palabra1 = Adjuntar()
palabra1.adjuntarTexto('hhhh')

print()
class Texto(Palabras, Adjuntar):
    
    def __init__(self):
        self.data = []

    def longitud(self, text):
        print(len(text))

f1 = Texto()
f1.Mayusc('no es mayúscula')    #de la clase Palabras
f1.adjuntarTexto('CicloFormativo')   # de la clase Adjuntar
f1.longitud('CampusVirtual')   # de la clase Texto
