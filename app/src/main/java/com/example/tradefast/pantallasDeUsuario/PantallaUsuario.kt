package com.example.tradefast.pantallasDeUsuario

import Adapter.ProductoAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tradefast.PantallaDetallesNovedades
import com.example.tradefast.R
import com.example.tradefast.objetos.ObjetoNovedad
import com.example.tradefast.sesion.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_pantalla_principal_novedades.*
import kotlinx.android.synthetic.main.activity_pantalla_usuario.*

class PantallaUsuario : AppCompatActivity() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var user = auth.currentUser
    val db = FirebaseFirestore.getInstance()
    var articulos: ArrayList<ObjetoNovedad>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_usuario)
        articulos = ArrayList()

        if (user != null) {
            llamarAdatos()
            rellenarHistorial()



            editarPerfil.setOnClickListener {
                val intent = Intent(this, PantallaPersonalizarPerfil::class.java)
                startActivity(intent)
            }

        } else {
            Toast.makeText(this@PantallaUsuario, "Se ha producido un error en el inicio de usuario", Toast.LENGTH_LONG)
                .show()
            finish()
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }


    }

    private fun llamarAdatos() {
        var correo: String = user!!.email!!
        db.collection("usuario").document(correo).get().addOnSuccessListener { documento ->
            if (documento.exists()) {
                val nombre = documento.getString("nombre")
                val apellido = documento.getString("apellido")
                val correo = documento.getString("correo")
                val edad = documento.getString("edad")
                nombrePerfil.text = "Nombre : $nombre"
                apellidoPerfil.text = "Apellido : $apellido"
                correPerfil.text = "Correo : $correo"
                edadPerfil.text = "edad = $edad"


            } else {
                Toast.makeText(this@PantallaUsuario, "error  en traer el nombre", Toast.LENGTH_LONG).show()
                finish()
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }

        }


    }

    private fun rellenarHistorial() {

        articulos!!.clear()
        var db = FirebaseFirestore.getInstance()

        db.collection("articulosVenta")
            .get()
            .addOnSuccessListener { documento ->
                for (x in documento) {

                    val nombre = x.getString("nombre")
                    val precio = x.getDouble("precio")
                    val descripcion = x.getString("descripcion")
                    val correo = x.getString("correoUsuario")
                    val id = x.getString("correoUsuario")
                    val foto = 2131230862
                    val vendido = x.getBoolean("vendido")
                    val comprado=x.getString("compradoPor")
                    if (vendido == true && comprado == user?.email) {
                       articulos!!.add(ObjetoNovedad(nombre!!, precio!!, descripcion!!, correo!!, foto, vendido!!,
                            id!!, comprado!!))
                    }

                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@PantallaUsuario, "error al cargar datos", Toast.LENGTH_LONG)
            }

        val adapter = ProductoAdapter(this@PantallaUsuario, articulos!!)
        historialArticulos.adapter = adapter
        historialArticulos.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, PantallaDetallesNovedades::class.java)
            intent.putExtra("producto", articulos!![position])
            startActivity(intent)
        }
    }
}




