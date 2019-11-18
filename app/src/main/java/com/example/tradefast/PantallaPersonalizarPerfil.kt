package com.example.tradefast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_pantalla_personalizar_perfil.*




class PantallaPersonalizarPerfil : AppCompatActivity() {

    private lateinit var dbreference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var cambiarNombre: EditText
    private lateinit var cambiarCorreo: EditText
    private lateinit var cambiarContrasena: EditText
    private lateinit var mFirebaseUser: FirebaseUser
    private lateinit var usuario:ObjetoUsuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_personalizar_perfil)

        cambiarNombre = findViewById(R.id.etCambioNombre)
        cambiarCorreo = findViewById(R.id.cambiarCorreo)
        cambiarContrasena=findViewById(R.id.cambiarContrasena)
        mFirebaseUser = auth.currentUser!!

        dbreference = database.getReference("User")

    }

    fun botonCambiar(view: View) {
        cambiarCuenta()
    }

    private fun cambiarCuenta() {

        val nombre:String=cambiarNombre.text.toString()
        val contrasena:String=cambiarContrasena.text.toString()
        val correo:String=cambiarCorreo.text.toString()





        var nuevoNombre : HashMap<String, String> = HashMap ()
        if(!TextUtils.isEmpty(nombre)){
            nuevoNombre["nombre"] = nombre
        }
        if (!TextUtils.isEmpty(contrasena)){
            nuevoNombre["contrasena"] = contrasena
            mFirebaseUser.updatePassword(contrasena).addOnCompleteListener(this) {
                    task ->
                if (task.isSuccessful){
                    Toast.makeText(this@PantallaPersonalizarPerfil,"Se ha cambiado el correo adecuadamente",Toast.LENGTH_LONG)
                }else{
                    task.exception is FirebaseException
                }
            }
        }
        if (!TextUtils.isEmpty(correo)){
            nuevoNombre["correo"] =correo
            mFirebaseUser.updateEmail(correo).addOnCompleteListener(this) {
                    task ->
                if (task.isSuccessful){
                    Toast.makeText(this@PantallaPersonalizarPerfil,"Se ha cambiado el correo adecuadamente",Toast.LENGTH_LONG)
                }else{
                    task.exception is FirebaseException
                }
            }

        }

        dbreference.child(usuario.id).setValue(nuevoNombre)

    }
}