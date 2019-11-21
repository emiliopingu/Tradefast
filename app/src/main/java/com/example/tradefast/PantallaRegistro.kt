package com.example.tradefast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class PantallaRegistro : AppCompatActivity() {

    private lateinit var RegistroNombre: EditText
    private lateinit var regsitroApellido: EditText
    private lateinit var RegistroContraseña: EditText
    private lateinit var RegistroContraseña2: EditText
    private lateinit var registroCorreoElectronico: EditText
    private lateinit var registroEdad: EditText
    private lateinit var barraProgreso: ProgressBar
    private lateinit var dbreference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registro)


        RegistroNombre = findViewById(R.id.RegistroNombre)
        regsitroApellido = findViewById(R.id.regsitroApellido)
        RegistroContraseña = findViewById(R.id.RegistroContraseña)
        RegistroContraseña2 = findViewById(R.id.RegistroContraseña2)
        registroCorreoElectronico = findViewById(R.id.registroCorreoElectronico)
        registroEdad = findViewById(R.id.registroEdad)

        barraProgreso = ProgressBar(this)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        dbreference = database.getReference("User")


    }

    fun botonDeRegsitro1(view: View) {
        crearCuenta()
    }

    private fun crearCuenta() {
        val nombre: String = RegistroNombre.text.toString()
        val apellido: String = regsitroApellido.text.toString()
        val contrasena: String = RegistroContraseña.text.toString()
        val contrasena2: String = RegistroContraseña2.text.toString()
        val correo: String = registroCorreoElectronico.text.toString()
        val edad: String = registroEdad.text.toString()

        if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(apellido) && !TextUtils.isEmpty(contrasena) &&
            !TextUtils.isEmpty(contrasena2) && !TextUtils.isEmpty(correo) && !TextUtils.isEmpty(edad)
        ) {
            if (contrasena.length < 6 && contrasena2.length < 6) {

                if (contrasena == contrasena2) {

                    if (edad != "0" || edad != "1" || edad != "2" || edad != "3" || edad != "4" || edad != "5" || edad != "6" || edad != "7" || edad != "8" ||
                        edad != "9" || edad != "10" || edad != "11" || edad == "12" || edad != "13" || edad != "14" || edad != "15" || edad != "16" || edad != "17"
                    ) {
                        progressBarLogin.visibility = View.VISIBLE

                        auth.createUserWithEmailAndPassword(correo, contrasena).addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val id: String? = dbreference.push().key
                                val datosUsuario = ObjetoUsuario(id, nombre, apellido, contrasena, correo, edad,0)
                                if (id != null) {
                                    dbreference.child("Usuario").child(id).setValue(datosUsuario)
                                    val IDparaVenta = Intent(this,VenderObjetos::class.java)
                                    intent.putExtra("idUsuarioVender", id)
                                    setResult(1,IDparaVenta)


                                }

                            } else {
                                if (task.exception is FirebaseAuthUserCollisionException) {
                                    Toast.makeText(this@PantallaRegistro, "Esta cuenta ya existe", Toast.LENGTH_LONG)
                                }
                            }
                        }
                        vistaLogin()

                    } else {
                        Toast.makeText(this@PantallaRegistro, "Necesitas ser mayor de edad", Toast.LENGTH_LONG)
                    }
                } else {
                    Toast.makeText(this@PantallaRegistro, "Escriba bien la contraseña", Toast.LENGTH_LONG)
                }


            } else {
                Toast.makeText(
                    this@PantallaRegistro,
                    "la contraseña debe de tener6 o mas caracteres",
                    Toast.LENGTH_LONG
                )
            }

        }
    }

    private fun vistaLogin() {
        startActivity(Intent(this, MainActivity::class.java))
    }

}
