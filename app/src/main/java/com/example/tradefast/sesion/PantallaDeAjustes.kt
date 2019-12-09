package com.example.tradefast.sesion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tradefast.pantallasPrincipales.PantallaPrincipalNovedades
import com.example.tradefast.R
import kotlinx.android.synthetic.main.activity_pantalla_de_ajustes.*

class PantallaDeAjustes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_de_ajustes)

        botonConfirmarAjustesVolumen.setOnClickListener {
            val intVolumnen = Intent(this, PantallaPrincipalNovedades::class.java)
            startActivity(intVolumnen)
        }
    }
}
