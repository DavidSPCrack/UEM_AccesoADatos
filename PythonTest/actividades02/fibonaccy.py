'''
fibonaccy.py

Created on 24/1/2015

@author: david.sancho
'''

def fib(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fib(n-1) + fib(n-2)
    
a = 15

for i in range(a):
    print(fib(i))