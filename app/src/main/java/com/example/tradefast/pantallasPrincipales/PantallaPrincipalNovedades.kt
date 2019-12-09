package com.example.tradefast.pantallasPrincipales


import Adapter.ProductoAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.tradefast.PantallaDetallesNovedades
import com.example.tradefast.R
import com.example.tradefast.VenderObjetos
import kotlinx.android.synthetic.main.activity_pantalla_principal_novedades.*
import com.example.tradefast.objetos.ObjetoNovedad
import com.example.tradefast.pantallasDeUsuario.PantallaUsuario
import com.example.tradefast.sesion.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration


class PantallaPrincipalNovedades : AppCompatActivity() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var novedades: ArrayList<ObjetoNovedad>? = null
    private lateinit var buscadorSubastas: EditText
    var user = auth.currentUser
    val db = FirebaseFirestore.getInstance()
    private lateinit var firestoreListener: ListenerRegistration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_novedades)
        novedades = ArrayList()

        buscadorSubastas = findViewById(R.id.buscadorSubastas)



        if (user != null) {
            llamarAdatos()
            rellenarRecycleViex()



            imagenUsuario1.setOnClickListener {
                val intent = Intent(this, PantallaUsuario::class.java)
                startActivity(intent)
            }
            botonSubastasPrincipal.setOnClickListener {
                val intent = Intent(this, PantallaPrincipalSubastas::class.java)
                startActivity(intent)
            }

            bVender1.setOnClickListener {
                val venderPantalla = Intent(this@PantallaPrincipalNovedades, VenderObjetos::class.java)
                startActivity(venderPantalla)
            }


        } else {

        }

    }


    private fun llamarAdatos() {
        var correo: String = user!!.email!!
        db.collection("usuario").document(correo).get().addOnSuccessListener { documento ->
            if (documento.exists()) {
                val nombre = documento.getString("nombre")
                tvNombreUsuarioNovedades.text = "Usuario $nombre"
            } else {
                Toast.makeText(this@PantallaPrincipalNovedades, "error  en traer el nombre", Toast.LENGTH_LONG).show()
                finish()
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }

        }


    }

    private fun rellenarRecycleViex() {

        var db=FirebaseFirestore.getInstance()
        db.collection("articulosVenta")
            .get()
            .addOnSuccessListener { documento ->
                for (x in documento) {
                    val nombre = x.getString("nombre")
                    val precio = x.getDouble("precio")
                    val descripcion=x.getString("descripcion")
                    val correo=x.getString("correoUsuario")
                    val foto= 0
                    val vendido=x.getBoolean("vendido")
                    val id=x.getString("id")
                    val comprado=x.getString("compradoPor")
                    if(vendido==false ) {
                        novedades!!.add(ObjetoNovedad(nombre!!, precio!!, descripcion!!, correo!!, foto, vendido, id!!,
                            comprado!!
                        ))

                    }
                }
            }
            .addOnFailureListener { exception ->
            Toast.makeText(this@PantallaPrincipalNovedades,"error al cargar datos",Toast.LENGTH_LONG)
            }

        val adapter = ProductoAdapter(this@PantallaPrincipalNovedades, novedades!!)
        listaNovedades.adapter = adapter
        listaNovedades.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, PantallaDetallesNovedades::class.java)
            intent.putExtra("producto", novedades!![position])
            startActivity(intent)
        }
    }

}



