package com.example.tradefast.pantallasDeUsuario

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tradefast.R
import com.example.tradefast.sesion.Login
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_pantalla_personalizar_perfil.*
import kotlinx.android.synthetic.main.activity_pantalla_principal_novedades.*


class PantallaPersonalizarPerfil : AppCompatActivity() {



    private lateinit var cambiarNombre: EditText
    private lateinit var cambiarCorreo: EditText
    private lateinit var cambiarContrasena: EditText
    private lateinit var botonConfirmarAjustePerfil: Button
    private lateinit var imagenPerfil: ImageView
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var user = auth.currentUser
    val db = FirebaseFirestore.getInstance()
    var storage: StorageReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_personalizar_perfil)

        cambiarCorreo = findViewById(R.id.cambiarCorreo)
        cambiarContrasena = findViewById(R.id.cambiarContrasena)
        cambiarNombre = findViewById(R.id.cambiarNombre)
        imagenPerfil = findViewById(R.id.imagenEditarPerfil)
        botonConfirmarAjustePerfil = findViewById(R.id.botonConfirmarAjustePerfil)
        storage = FirebaseStorage.getInstance().reference

        imagenPerfil.setOnClickListener {
            cargarImagen()
        }



        if (user != null) {
            botonConfirmarAjustePerfil.setOnClickListener {
                cambiarCuenta()
                finish()
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        } else {
            Toast.makeText(
                this@PantallaPersonalizarPerfil,
                "Se ha producido un error en el login de usuario",
                Toast.LENGTH_LONG
            ).show()
            finish()
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }


    private fun cargarImagen() {
        var i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(i, "Escoja una imagen "), 1)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && resultCode == 1 && data != null && data.data != null) {


            val imagen = data!!.data
            val path: StorageReference = storage!!.child("fotosUsuario").child(imagen.lastPathSegment)

            val uploadTask = path.putFile(imagen)


            val urlTask = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                path.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    Glide.with(this).load(downloadUri)
                        .fitCenter().centerCrop().into(imagenEditarPerfil)

                    Glide.with(PantallaUsuario::class.objectInstance!!).load(downloadUri)
                        .fitCenter().centerCrop().into(imagenEditarPerfil)


                } else {
                    Toast.makeText(this, "No se ha descargado con exito", Toast.LENGTH_LONG)
                }
            }

        }


    }


    private fun cambiarCuenta() {
        val correo: String = cambiarCorreo.text.toString()
        val contra: String = cambiarContrasena.text.toString()
        val nombre: String = cambiarNombre.text.toString()
        val correoUsuario = user!!.email

        if (!TextUtils.isEmpty(correo)) {
            user!!.updateEmail(correo).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@PantallaPersonalizarPerfil,
                        "Se ha cambiado el correo adecuadamente",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    task.exception is FirebaseException
                }

            }


        }
        db.collection("usuario")
            .get()
            .addOnSuccessListener { documento ->
                for (x in documento) {
                    val correoDatos = x.getString("correo")
                    val id = x.getString("id")
                    if (correoUsuario ==correoDatos) {
                        db.collection("usuario").document(id!!).get().addOnSuccessListener { documento ->
                            if (documento.exists()) {
                                if (!TextUtils.isEmpty(correo)) {

                                    db.collection("articulosVenta")
                                        .get()
                                        .addOnSuccessListener { documento ->
                                            for (x in documento) {
                                                val comprador = x.getString("compradoPor")
                                                val id = x.getString("id")
                                                if (comprador == correoDatos) {
                                                    db.collection("articulosVenta").document(id!!)
                                                        .update(
                                                            mapOf(
                                                                "compradoPor" to correo
                                                            )
                                                        )


                                                }

                                            }
                                        }
                                    db.collection("articulosVenta")
                                        .get()
                                        .addOnSuccessListener { documento ->
                                            for (x in documento) {
                                                val usuarioCorreo = x.getString("correoUsuario")
                                                val id = x.getString("id")
                                                if (usuarioCorreo == correoDatos) {
                                                    db.collection("articulosVenta").document(id!!)
                                                        .update(
                                                            mapOf(
                                                                "correoUsuario" to correo
                                                            )
                                                        )


                                                }

                                            }
                                        }
                                    db.collection("usuario").document(id!!)
                                        .update(
                                            mapOf(
                                                "correo" to correo
                                            )
                                        )
                                }
                                if (!TextUtils.isEmpty(nombre)) {
                                    db.collection("usuario").document(id)
                                        .update(
                                            mapOf(
                                                "nombre" to nombre
                                            )
                                        )
                                }
                                if (!TextUtils.isEmpty(contra)) {
                                    db.collection("usuario").document(id)
                                        .update(
                                            mapOf(
                                                "contrasena" to contra
                                            )
                                        )
                                    user!!.updatePassword(contra).addOnCompleteListener(this) { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(
                                                this@PantallaPersonalizarPerfil,
                                                "Se ha cambiado el correo adecuadamente",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        } else {
                                            task.exception is FirebaseException
                                        }
                                    }
                                }
                            }else{
                                Toast.makeText(this@PantallaPersonalizarPerfil, "error  en traer datos", Toast.LENGTH_LONG).show()
                                finish()
                                FirebaseAuth.getInstance().signOut()
                                val intent = Intent(this, Login::class.java)
                                startActivity(intent)
                            }


                        }
                    }

                }
            }

    }
}