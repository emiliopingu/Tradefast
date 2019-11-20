package com.example.tradefast

class ObjetoUsuario(id: String?, nombre: String, apellido: String, contrasena: String, correo: String, edad: String , imagen:Int) {

    var id: String = ""
    var nombre: String = ""
    var apellido: String = ""
    var contrasena: String = ""
    var correo: String = ""
    var edad: String = ""
    var imagen:Int=0

    init {
        this.id = id!!
        this.nombre = nombre
        this.apellido = apellido
        this.contrasena = contrasena
        this.correo = correo
        this.edad = edad
        this.imagen=imagen
    }


}