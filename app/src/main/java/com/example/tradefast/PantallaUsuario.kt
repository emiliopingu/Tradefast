package com.example.tradefast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_pantalla_principal_novedades.*
import kotlinx.android.synthetic.main.activity_pantalla_usuario.*

class PantallaUsuario : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_usuario)

        var nombreUsuario = intent.getStringExtra("nombrePerfil")
        nombreInfoUsuario.text ="Nombre del usuario $nombreUsuario"
        var c=intent.getStringExtra("contraPerfil")

        editarPerfil.setOnClickListener {
            val edit = Intent(this, PantallaPersonalizarPerfil::class.java)
            startActivity(edit)
        }
    }
}

