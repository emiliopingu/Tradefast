package com.example.tradefast.pantallasPrincipales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tradefast.R

import com.example.tradefast.objetos.ObjetoSubasta
import com.example.tradefast.pantallasDeUsuario.PantallaUsuario
import com.example.tradefast.sesion.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_pantalla_principal_subastas.*


class PantallaPrincipalSubastas : AppCompatActivity() {

    var subastas:ArrayList<ObjetoSubasta>?=null
    var layoutManager: RecyclerView.LayoutManager?=null

    var lista: RecyclerView?=null
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var user = auth.currentUser
    val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_subastas)


        if(user!=null){
            llamarAdatos()
            imagenUsuario2.setOnClickListener {
                val imagen1 = Intent(this, PantallaUsuario::class.java)
                startActivity(imagen1) }

            botonNovedadesSecundario.setOnClickListener {
                val intNovedades = Intent(this, PantallaPrincipalNovedades::class.java)
                startActivity(intNovedades)
            }
        }





       subastas = ArrayList()







    }

    private fun llamarAdatos() {
        var correo: String = user!!.email!!
        db.collection("usuario").document(correo).get().addOnSuccessListener { documento ->
            if (documento.exists()) {
                val nombre = documento.getString("nombre")
                subastaNombre.text = "Usuario :  $nombre"
            } else {
                Toast.makeText(this@PantallaPrincipalSubastas, "error  en traer el nombre", Toast.LENGTH_LONG).show()
                finish()
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }

        }


    }
}
