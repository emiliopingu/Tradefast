package com.example.tradefast

class ObjetoUsuario(id: String?, nombre: String, apellido: String, contrasena: String, correo: String, edad: String) {

    var id: String = ""
    var nombre: String = ""
    var apellido: String = ""
    var contrasena: String = ""
    var correo: String = ""
    var edad: String = ""

    init {
        this.id = id!!
        this.nombre = nombre
        this.apellido = apellido
        this.contrasena = contrasena
        this.correo = correo
        this.edad = edad
    }


}