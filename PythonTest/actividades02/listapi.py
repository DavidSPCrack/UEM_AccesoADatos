'''
listapi.py

Created on 24/1/2015

@author: david.sancho
'''
import math

def rpi(n):
    return round(math.pi, n)

listaPi = []

for i in range(1, 7):
    listaPi.append(rpi(i))
    
print(listaPi)