package com.example.tradefast

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
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
    private lateinit var imagenPerfil:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_personalizar_perfil)

        cambiarNombre = findViewById(R.id.etCambioNombre)
        cambiarCorreo = findViewById(R.id.cambiarCorreo)
        cambiarContrasena=findViewById(R.id.cambiarContrasena)
        imagenPerfil=findViewById(R.id.imagenPerfil)
        imagenPerfil.setOnClickListener {
            cargarImagen()
        }
        mFirebaseUser = auth.currentUser!!

        dbreference = database.getReference("User")

    }

    fun botonCambiar(view: View) {
        cambiarCuenta()
    }
    private fun cargarImagen() {
        var i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        i.type = "image/"
        i.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(i, "Escoja una imagen "), 9)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== Activity.RESULT_OK){
            val path: Uri = data!!.data
            imagenPerfil.setImageURI(path)

        }
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