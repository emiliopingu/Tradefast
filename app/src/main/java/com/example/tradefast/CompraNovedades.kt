package com.example.tradefast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_compra_novedades.*

class CompraNovedades : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compra_novedades)

        val nombreCompra = intent.getStringExtra("nombreNovedadCompra")
        val precioCompra = intent.getDoubleExtra("precioNovedadCompra", 0.0)
        val usuario = intent.getStringExtra("usuarioNovedadCompra")
        val fotoCompra = intent.getIntExtra("fotoNovedadCompra", 0)
        val despCompra = intent.getStringExtra("despNovedadCompra")

         nombreArticuloCompra.text="Artículo : $nombreCompra"
        descripcionArticuloNovedades.text="Usuario : $usuario \n Descripcion : $despCompra"
        fotoCompraObjeto.setImageResource(fotoCompra)
        precioArticuloNovedades.text=" Su precio es : $precioCompra €"


        comprarAticuloNovedades.setOnClickListener {
            val comprar = Intent(this, RealizarCompra::class.java)
            intent.putExtra("precioCompra",precioCompra)
            startActivity(comprar)
        }
    }
}
