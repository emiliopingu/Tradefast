package com.example.tradefast.sesion

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.example.tradefast.pantallasPrincipales.PantallaPrincipalNovedades
import com.example.tradefast.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser

import kotlinx.android.synthetic.main.activity_main.*

class Login : AppCompatActivity() {

    private lateinit var nombreInicio: EditText
    private lateinit var contrasenaInicio: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var barraProgreso: ProgressBar
    private lateinit var imagenLogin:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nombreInicio = findViewById(R.id.nombreInicio)
        contrasenaInicio = findViewById(R.id.contraseñaInicio)
        auth = FirebaseAuth.getInstance()
        barraProgreso = ProgressBar(this)
        imagenLogin= findViewById(R.id.imagenLogin)



        olvidarContraseña.setOnClickListener {
            val mandarAcorreo = Intent(this, PantallaOlvidarContrasena::class.java)
            startActivity(mandarAcorreo)
        }

        botonRegistrarse.setOnClickListener {
            val intRegistrar = Intent(this, PantallaRegistro::class.java)
            startActivity(intRegistrar)
        }
    }


    fun botonLogin(view: View) {
        loginUsuario()
    }


    private fun loginUsuario() {
        val usuario: String = nombreInicio.text.toString()
        val contrasenaInicio: String = contraseñaInicio.text.toString()
        if (!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(contrasenaInicio)) {
            if (contrasenaInicio.length >= 6) {
                auth.signInWithEmailAndPassword(usuario, contrasenaInicio)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            vistaNovedades(user)
                        } else {
                            if (task.exception is FirebaseAuthUserCollisionException) {
                                Toast.makeText(
                                    this,
                                    "Error de autentificación vuelve a escribir los datos",
                                    Toast.LENGTH_LONG
                                ).show()
                            }


                        }
                    }
            } else {
                Toast.makeText(
                    this@Login,
                    "la contraseña debe tener al menos 6 caracteres o mas",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun vistaNovedades(u: FirebaseUser?) {
        val intent = Intent(this@Login, PantallaPrincipalNovedades::class.java)
        intent.putExtra("UsuarioNovedades", u)
        startActivity(intent)

    }


}
