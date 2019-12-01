package com.example.tradefast.objetos

class ObjetoSubasta(nombre:String,pujaInicial:Double,pujaFinal:Double,usuario:String,foto:Int) {

    var nombre:String=""
    var pujaInicial:Double=0.0
    var pujaFinal:Double=0.0
    var usuario:String=""
    var foto:Int=0

    init {
        this.nombre = nombre
        this.pujaInicial = pujaInicial
        this.pujaFinal = pujaFinal
        this.usuario = usuario
        this.foto = foto
    }
}