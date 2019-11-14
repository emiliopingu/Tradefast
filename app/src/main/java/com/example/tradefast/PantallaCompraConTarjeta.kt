package com.example.tradefast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pantalla_compra_con_tarjeta.*

class PantallaCompraConTarjeta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_compra_con_tarjeta)

        botonConfirmarCompra.setOnClickListener {
            val intConfirmarCompra = Intent(this, PantallaPrincipalNovedades::class.java)
            startActivity(intConfirmarCompra)
        }

        botonCancelarCompra.setOnClickListener {
            val intCancelarCompra = Intent(this,PantallaPrincipalNovedades::class.java)
            startActivity(intCancelarCompra)
        }
    }
}
