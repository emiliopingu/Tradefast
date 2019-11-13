package com.example.tradefast

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var nombreInicio: EditText
    private lateinit var contrasenaInicio: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombreInicio = findViewById(R.id.nombreInicio)
        contrasenaInicio = findViewById(R.id.contraseñaInicio)
        auth = FirebaseAuth.getInstance()


        botonIniciarSecion.setOnClickListener {
            val intIniciarSecion = Intent(this, PantallaPrincipalNovedades::class.java)
            startActivity(intIniciarSecion)
        }

        olvidarContraseña.setOnClickListener {
            val mandarAcorreo = Intent(this, PantallaOlvidarContrasena::class.java)
            startActivity(mandarAcorreo)
        }

        botonRegistrarse.setOnClickListener {
            val intRegistrar = Intent(this, PantallaRegistro::class.java)
            startActivity(intRegistrar)
        }
    }





    private fun loginUsuario() {
        val usuario: String = nombreInicio.text.toString()
        val contraseñaInicio: String = contraseñaInicio.text.toString()

        if (!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(contraseñaInicio)) {

            auth.signInWithEmailAndPassword(usuario, contraseñaInicio)
                .addOnCompleteListener(this) {
                    task->
                    if(task.isSuccessful){
                        //vistaRegistrar()
                    }else{
                        Toast.makeText(this,"Error de autentificación vuelve a escribir los datos",Toast.LENGTH_LONG)
                    }
                }
        }

    }


}
