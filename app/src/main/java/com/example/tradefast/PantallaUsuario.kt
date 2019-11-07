package com.example.tradefast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pantalla_principal_novedades.*
import kotlinx.android.synthetic.main.activity_pantalla_usuario.*

class PantallaUsuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_usuario)


        editarPerfil.setOnClickListener {
            val edit = Intent(this,EditarPerfil::class.java)
            startActivity(edit)
        }
    }
}
