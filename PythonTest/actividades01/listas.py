'''
listas.py

Created on 17/1/2015

@author: david.sancho
'''

mares1 = ["mediterráneo", "cantábrico", "báltico", "adriático", "tirreno", "egeo"]
mares2 = ["rojo", "muerto", "caspio", "negro", "arábigo", "sulu"]
mares = mares1 + mares2

print ("La longitud de la lista mares1 es de", len(mares1), "posiciones" )
print ("Contenido de la lista mares1:", mares1 )
print ("La longitud de la lista mares2 es de", len(mares2), "posiciones" )
print ("Contenido de la lista mares2:", mares2 )
print ("La longitud de la lista mares es de", len(mares), "posiciones" )
print ("Contenido de la lista mares:", mares )
print ("Las 3 primeras posiciones de mares1 son:", mares1[0:3] )
print ("Las 3 ultimas posiciones de mares2 son:", mares1[3:6] )
print ("La posición del mar caspio en mares2 es:", mares2.index("caspio"))
print ("La posición del mar caspio en mares es:", mares.index("caspio"))