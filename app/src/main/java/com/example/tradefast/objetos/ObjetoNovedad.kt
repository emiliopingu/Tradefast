package com.example.tradefast.objetos

class ObjetoNovedad(nombre: String, precio: Double, descripcion: String, usuario: String, foto: Int) {

    var nombre: String = ""
    var precio: Double = 0.0
    var usuario: String = ""
    var foto: Int = 0
    var descripcion: String = ""

    init {
        this.nombre = nombre
        this.precio = precio
        this.usuario = usuario
        this.foto = foto
        this.descripcion = descripcion
    }
}