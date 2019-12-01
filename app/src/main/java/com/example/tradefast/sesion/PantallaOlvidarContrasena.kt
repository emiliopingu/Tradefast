package com.example.tradefast.sesion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.tradefast.R
import com.google.firebase.auth.FirebaseAuth

class PantallaOlvidarContrasena : AppCompatActivity() {

    private lateinit var reCorreo: EditText
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_olvidar_contrasena)

        reCorreo = findViewById(R.id.reCorreo)
        auth = FirebaseAuth.getInstance()
    }

    fun enviarCorreo(view: View) {
        val correo = reCorreo.text.toString()

        if (!TextUtils.isEmpty(correo)) {
            auth.sendPasswordResetEmail(correo)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val mandarLogin = Intent(this, MainActivity::class.java)
                        startActivity(mandarLogin)
                    } else {
                        Toast.makeText(this, "Se ha producido un error con el correo", Toast.LENGTH_LONG)
                    }

                }
        }
    }
}
