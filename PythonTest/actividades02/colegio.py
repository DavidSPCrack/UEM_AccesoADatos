'''
Created on 31/01/2015

@author: David
'''
        
        
def mostrarTitulo(titulo):
    print("-" * 30)
    print(" " * 6, titulo)
    print("-" * 30)
    
def inputInt(text):
    while(True):
        try:
            return int(input(text))
        except ValueError:
            print("Introduce un valor númerico")
        
class Alumno():    
    def _init_(self, nombre, asignatura1, asignatura2, asignatura3, nota11, nota12, nota13, nota21, nota22, nota23, nota31, nota32, nota33):
        self.nombre = nombre
        self.asignatura1 = asignatura1
        self.asignatura2 = asignatura2
        self.asignatura3 = asignatura3
        self.nota11 = nota11
        self.nota12 = nota12
        self.nota13 = nota13
        self.nota21 = nota21
        self.nota22 = nota22
        self.nota23 = nota23
        self.nota31 = nota31
        self.nota32 = nota32
        self.nota33 = nota33


listaAlumnos = []

opcion = 1
prompt = '> '

while(opcion != 6):
    try:
        
        mostrarTitulo("MENU PRINCIPAL")
        print("Seleccione una opción:")
        print(" 1) Añadir Alumno")
        print(" 2) Eliminar Alumno ")
        print(" 3) Mostrar Notas ")
        print(" 4) Almacenar Notas ")
        print(" 5) Mostrar Menú ")
        print(" 6) Salir ")
        opcion = inputInt(prompt)
        
        if(opcion == 1):
            mostrarTitulo("AÑADIR ALUMNO")
            nombre = input("Introduzca su nombre " + prompt)
            
            
            mostrarTitulo("ASIGNATURAS DEL ALUMNO")
            asignatura1 = input("Nombre Asignatura 1 " + prompt)
            print("Notas Asignatura 1")
            nota11 = inputInt("Nota 1: ")
            nota12 = inputInt("Nota 2: ")
            nota13 = inputInt("Nota 3: ")
            
            asignatura2 = input("Nombre Asignatura 2 " + prompt)
            print("Notas Asignatura 2")
            nota21 = inputInt("Nota 1: ")
            nota22 = inputInt("Nota 2: ")
            nota23 = inputInt("Nota 3: ")
            
            
            asignatura3 = input("Nombre Asignatura 3 " + prompt)
            print("Notas Asignatura 3")
            nota31 = inputInt("Nota 1: ")
            nota32 = inputInt("Nota 2: ")
            nota33 = inputInt("Nota 3: ")

            a = Alumno()
            a._init_(nombre, asignatura1, asignatura2, asignatura3, nota11, nota12, nota13, nota21, nota22, nota23, nota31, nota32, nota33)
                 
            listaAlumnos.append(a)
            
            a = ""
        elif (opcion == 2):
            contador = 1
            mostrarTitulo("ELIMINAR ALUMNO")
            print("Elija un alumno a eliminar:")
            for alumno in listaAlumnos:
                print(str(contador) + ")", alumno.nombre)
                contador = contador + 1
                
            valor = inputInt(prompt)
            if((valor < contador) and (valor > 0)):
                listaAlumnos.pop(valor - 1)
            else:
                print("Valor fuera de rango.")
            
        elif (opcion == 3):
            print("Las notas de los alumnos matriculados son:")
            for alumno in listaAlumnos:
                print(alumno.nombre, "sus notas son:")
                print(alumno.asignatura1, ":", alumno.nota11, ",", alumno.nota12, ",", alumno.nota13)
                print(alumno.asignatura2, ":", alumno.nota21, ",", alumno.nota22, ",", alumno.nota23)
                print(alumno.asignatura3, ":", alumno.nota31, ",", alumno.nota32, ",", alumno.nota33)                
                
        elif (opcion == 4):
            contador = 1
            mostrarTitulo("ALMACENAR NOTAS")
            print("Elige un alumno para cambiar sus notas")
            for alumno in listaAlumnos:
                print(str(contador) + ")", alumno.nombre)
                contador = contador + 1
            
            try:
                valor = inputInt(prompt)
                if((valor < contador) and (valor > 0)):
                    b = listaAlumnos[valor - 1]
                    try:
                        print("Escriba el número de la asignatura del alumno", b.nombre, "a modificar.")
                        print("1)", b.asignatura1)
                        print("2)", b.asignatura2)
                        print("3)", b.asignatura3)
                        opcion2 = inputInt(prompt)
                    
                    
                        if(opcion2 == 1):
                            print("Escriba el número de la nota de la asignatura", b.asignatura1, "del alumno", b.nombre, "a modificar.")
                            print("1)", b.nota11)
                            print("2)", b.nota12)
                            print("3)", b.nota13)
                            opcion3 = inputInt(prompt)
                            
                            if(opcion3 == 1):
                                b.nota11 = inputInt("Introduzca el nuevo valor " +prompt)
                            elif(opcion3 == 2):
                                b.nota12 = inputInt("Introduzca el nuevo valor " +prompt)
                            elif(opcion3 == 3):
                                b.nota13 = inputInt("Introduzca el nuevo valor " +prompt)
                                
                        elif(opcion2 == 2):
                            print("Escriba el número de la nota de la asignatura", b.asignatura2, "del alumno", b.nombre, "a modificar.")
                            print("1)", b.nota21)
                            print("2)", b.nota22)
                            print("3)", b.nota23)
                            opcion3 = inputInt(prompt)
                            
                            if(opcion3 == 1):
                                b.nota21 = inputInt("Introduzca el nuevo valor " +prompt)
                            elif(opcion3 == 2):
                                b.nota22 = inputInt("Introduzca el nuevo valor " +prompt)
                            elif(opcion3 == 3):
                                b.nota23 = inputInt("Introduzca el nuevo valor " +prompt)
                        elif(opcion2 == 3):
                            print("Escriba el número de la nota de la asignatura", b.asignatura1, "del alumno", b.nombre, "a modificar.")
                            print("1)", b.nota31)
                            print("2)", b.nota32)
                            print("3)", b.nota33)
                            opcion3 = inputInt(prompt)
                            
                            if(opcion3 == 1):
                                b.nota31 = inputInt("Introduzca el nuevo valor " +prompt)
                            elif(opcion3 == 2):
                                b.nota32 = inputInt("Introduzca el nuevo valor " +prompt)
                            elif(opcion3 == 3):
                                b.nota33 = inputInt("Introduzca el nuevo valor " +prompt)
                    except ValueError:
                        print("Valor fuera de rango.")

                else:
                    print("Valor fuera de rango.")
            except ValueError:
                print("No es un valor adecuado.")
            
            
        elif (opcion == 5):
            print("Los alumnos matriculados son:")
            for alumno in listaAlumnos:
                print(alumno.nombre)
        else:
            print("Fin del programa.")
        
    except ValueError:
        print("La opción marcada no es correcta.")


        
    
    
