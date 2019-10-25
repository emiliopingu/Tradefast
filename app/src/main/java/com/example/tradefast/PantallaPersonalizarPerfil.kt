package com.example.tradefast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pantalla_personalizar_perfil.*

class PantallaPersonalizarPerfil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_personalizar_perfil)

        botonConfirmarAjustePerfil.setOnClickListener {
            val intPerfilAjuste = Intent(this,PantallaPrincipalNovedades::class.java)
            startActivity(intPerfilAjuste)
        }
    }
}
