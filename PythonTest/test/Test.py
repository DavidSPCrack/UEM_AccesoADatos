class Person():
    __name = ""
    __address = ""
    
    def __init__(self, name, address):
        self.__name = name
        self.__address = address
        
    def get_name(self):
        return self.__name
    
    def set_name(self, name):
        self.__name = name
        
    def get_address(self):
        return self.__address
    
    def set_address(self, address):
        self.__address = address
    
    def toString(self):
        __cadena = self.get_name() + " " + self.get_address()
        print(__cadena)
        
def main():
    person = Person("Juan", "Calle Mayor")
    print(person.toString())
    
    
if __name__ == '__main__': 
    main()