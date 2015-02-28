# -*- encoding: utf-8 -*-
 
{
    "name": "Demo",
    "version": "1.0",
    "description": """
        Modulo para fines educativos
    """,
    "author": "Universidad Europea de Madrid",
    "website": "http://www.uem.es",
    "category": "",
    "depends": [
            "base",#Para instalarse este módulo debe tener el modulo base instalado
            "web",#Para instalarse este módulo debe tener el modulo web instalado
                ],
    "data":[
            "demo_view.xml", #todos los archivos xml que posea nuestro modulo se deben agregar aquí
                ],
    "demo_xml": [
                        ],
    "update_xml": [
                        ],
    "active": False,
    "installable": True,
}