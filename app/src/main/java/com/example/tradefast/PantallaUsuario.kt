package com.example.tradefast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.android.synthetic.main.activity_pantalla_usuario.*

class PantallaUsuario : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_usuario)
        val nombreUsuario = intent.getStringExtra("nombrePerfil")
        nombreInfoUsuario.text ="Nombre del usuario $nombreUsuario"

        val correo=intent.getStringExtra("correoPerfil")
        correPerfil.text=correo

        editarPerfil.setOnClickListener {
            infoEditarPerfil()
        }
    }

    private fun infoEditarPerfil() {
        val contra=intent.getStringExtra("contraPerfil")
        auth.signInWithEmailAndPassword(correPerfil.text.toString(), contra)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    vistaNovedades(correPerfil.text.toString(), contra)
                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        Toast.makeText(
                            this,
                            "Error de autentificación vuelve a escribir los datos",
                            Toast.LENGTH_LONG
                        )
                    }


                }
            }

    }


    private fun vistaNovedades(correo: String, contra: String) {
        val pos: Int = correo.indexOf("@")
        val user: String = correo.substring(0, pos)
        val intent = Intent(this, PantallaPersonalizarPerfil::class.java)
        intent.putExtra("nombrePerfilEditar", user)
        intent.putExtra("contraPerfilEditar", contra)
        intent.putExtra("correoPerfilEditar",correo)
        startActivity(intent)
    }

}

