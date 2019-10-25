package com.example.tradefast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pantalla_subir_puja.*

class PantallaSubirPuja : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_subir_puja)

        botonSubirPujaNotificacion.setOnClickListener {
            val intNovedades = Intent(this, PantallaPrincipalNovedades::class.java)
            startActivity(intNovedades)
        }
        botonCancelarSubidaDePuja.setOnClickListener {
            val intNovedades = Intent(this, PantallaPrincipalNovedades::class.java)
            startActivity(intNovedades)
        }
    }
}
