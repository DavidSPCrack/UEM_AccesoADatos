'''
Created on 21/2/2015

@author: usuario.apellido
'''
print()

# Función definida fuera de la clase
def area(self, base, altura):
    s = base*altura
    return s

class Figura:
    def __init__(self, nombre):  # Constructor de la clase
        self.nombre = nombre
        
     #Asignación de la función area a una variable de la clase
    spfc = area   

    def mensaje(self):
        return 'Se va a calcular el area de la figura ' 
    h = mensaje         # Se puede asignar una función a una variable dentro de la clase
    
obj1 = Figura('Rectángulo') 
print(obj1.h())
print(obj1.spfc(2,5))




print()

# Los Métodos pueden llamar a otros métodos mediante atributos del método self:

class Articulos:
    def __init__(self):
        self.data = []
    def add(self, x):
        self.data.append(x)
    def addtwice(self, x):
        self.add(x)
        self.add(x)
        
compra1 = Articulos()
compra1.add('Pan')
compra1.add('Jamón')
compra1.addtwice('Vino')
print(compra1.data)
