package com.example.tradefast

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tradefast.objetos.ObjetoNovedad
import com.example.tradefast.pantallasPrincipales.PantallaPrincipalNovedades
import com.example.tradefast.sesion.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_vender_objetos.*
import java.util.*


class VenderObjetos : AppCompatActivity() {

    private lateinit var nombreDeVenta: EditText
    private lateinit var precioDeVenta: EditText
    private lateinit var descripcionDeVenta: EditText
    private lateinit var imagenDeVenta: ImageView

    private lateinit var barraProgreso: ProgressBar
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var user = auth.currentUser
    val db = FirebaseFirestore.getInstance()


    val id: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vender_objetos)

        nombreDeVenta = findViewById(R.id.aNombreVender)

        precioDeVenta = findViewById(R.id.aPrecioVender)

        descripcionDeVenta = findViewById(R.id.aDescripVender)

        imagenDeVenta = findViewById(R.id.imagenVenta)

        imagenDeVenta.setOnClickListener {
            cargarImagen()
        }

        barraProgreso = ProgressBar(this)


        aceptarVenta.setOnClickListener {
            crearArticulo()
        }


    }

    private fun cargarImagen() {
        var i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        i.type = "image/"
        i.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(i, "Escoja una imagen "), 10)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val path: Uri = data!!.data
            imagenDeVenta.setImageURI(path)

        }
    }


    private fun crearArticulo() {
        if (user != null) {
            val precio: String = precioDeVenta.text.toString()
            val nombreArticulo = nombreDeVenta.text.toString()
            val descripcion = descripcionDeVenta.text.toString()
            val id=   UUID.randomUUID()
            if (!TextUtils.isEmpty(nombreDeVenta.text) && !TextUtils.isEmpty(precioDeVenta.text) &&
                !TextUtils.isEmpty(descripcionDeVenta.text)
            ) {
                if (esUnNumero(precio)) {
                    var preciod = java.lang.Double.parseDouble(precio)
                    if (preciod > 0) {
                        val articuloNovedades = ObjetoNovedad(
                            nombreArticulo, preciod, descripcion, user?.email!!, imagenDeVenta.id, false
                        ,id.toString(),"")
                        db.collection("articulosVenta").document(id.toString()).set(articuloNovedades)
                            .addOnSuccessListener { documentReference ->
                                Toast.makeText(
                                    this@VenderObjetos,
                                    "Se ha introducido correctamente el articulo",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(
                                    this@VenderObjetos,
                                    "No se ha introducido correctamente el articulo",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                    } else {
                        Toast.makeText(this@VenderObjetos, "El precio es menor o igual que cero", Toast.LENGTH_LONG).show()

                    }
                } else {
                    Toast.makeText(this@VenderObjetos, "El precio tiene una o varias letras", Toast.LENGTH_LONG).show()
                }

            } else {
                Toast.makeText(this@VenderObjetos, "Rellena todo los campos", Toast.LENGTH_LONG).show()
            }
            val irNovedad = Intent(this@VenderObjetos, PantallaPrincipalNovedades::class.java)
            startActivity(irNovedad)
        } else {
            Toast.makeText(this@VenderObjetos, "error al cargar usuario", Toast.LENGTH_LONG).show()
            finish()
            val errorPerfil = Intent(this@VenderObjetos, Login::class.java)
            startActivity(errorPerfil)
            FirebaseAuth.getInstance().signOut()
        }

    }

    private fun esUnNumero(cadena: String): Boolean {
        try {
            Integer.parseInt(cadena)
            return true
        } catch (nfe: NumberFormatException) {
            return false
        }

    }
}












