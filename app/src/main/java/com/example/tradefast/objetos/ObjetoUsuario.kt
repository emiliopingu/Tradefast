package com.example.tradefast.objetos

class ObjetoUsuario(
     nombre: String, apellido: String, contrasena: String, correo: String, edad: String, imagen:Int,
    articulos: ArrayList<ObjetoNovedad>?
) {
    var nombre: String = ""
    var apellido: String = ""
    var contrasena: String = ""
    var correo: String = ""
    var edad: String = ""
    var imagen:Int=0
    var articulos:ArrayList<ObjetoNovedad>?=null

    init {
        this.nombre = nombre
        this.apellido = apellido
        this.contrasena = contrasena
        this.correo = correo
        this.edad = edad
        this.imagen=imagen
        this.articulos=articulos
    }


}