package com.example.tradefast

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import com.example.tradefast.objetos.ObjetoNovedad
import com.example.tradefast.sesion.Login
import kotlinx.android.synthetic.main.activity_vender_objetos.*


class VenderObjetos : AppCompatActivity() {

    private lateinit var nombreDeVenta: EditText
    private lateinit var precioDeVenta: EditText
    private lateinit var descripcionDeVenta: EditText
    private lateinit var imagenDeVenta: ImageView

    private lateinit var barraProgreso: ProgressBar
    private lateinit var dbreference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

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
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        dbreference = database.getReference("User")

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

    private fun vistaNovedades() {
        startActivity(Intent(this, Login::class.java))
    }

    private fun crearArticulo() {
        val precio: String = precioDeVenta.text.toString()
        if (!TextUtils.isEmpty(nombreDeVenta.text) && !TextUtils.isEmpty(precioDeVenta.text) &&
            !TextUtils.isEmpty(descripcionDeVenta.text)
        ) {
            var preciod = java.lang.Double.parseDouble(precio)
            val id: String? = dbreference.push().key
            val objeto = ObjetoNovedad(
                nombreDeVenta.text.toString(),
                preciod,
                descripcionDeVenta.text.toString(),
                "",
                imagenDeVenta.id
            )
            if (id != null) {
                dbreference.child("ArticulosEnVentas").child(id).setValue(objeto)
            }
            vistaNovedades()
        }
    }
}












