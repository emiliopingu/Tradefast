package com.example.tradefast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pantalla_de_ajustes.*

class PantallaDeAjustes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_de_ajustes)

        botonConfirmarAjustesVolumen.setOnClickListener {
            val intVolumnen= Intent(this, PantallaPrincipalNovedades::class.java)
            startActivity(intVolumnen)
        }
    }
}
