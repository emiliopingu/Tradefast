package com.example.tradefast.objetos

import java.io.Serializable

class ObjetoNovedad(nombre: String, precio: Double, descripcion: String, correoUsuario: String,
                    foto: Int , vendido:Boolean , id:String , compradoPor:String) : Serializable {

    var nombre: String = ""
    var precio: Double = 0.0
    var correoUsuario: String = ""
    var foto: Int = 0
    var descripcion: String = ""
    var vendido:Boolean = false
    var id:String=""
    var compradoPor:String=""

    init {
        this.nombre = nombre
        this.precio = precio
        this.correoUsuario=correoUsuario
        this.foto = foto
        this.descripcion = descripcion
        this.vendido=vendido
        this.id=id
        this.compradoPor=compradoPor
    }
}