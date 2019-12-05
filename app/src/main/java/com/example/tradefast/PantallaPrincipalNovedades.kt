package com.example.tradefast

import Adapter.AdapterNovedades
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pantalla_principal_novedades.*
import com.example.tradefast.objetos.ObjetoNovedad
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

import com.google.firebase.database.DataSnapshot




class PantallaPrincipalNovedades : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var novedades: ArrayList<ObjetoNovedad>? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    private var nombreUsuariotv: TextView? = null
    var adaptador: AdapterNovedades? = null
    var data: FirebaseDatabase? = null
    var lista: RecyclerView? = null
    private lateinit var buscadorSubastas: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_novedades)

        data = FirebaseDatabase.getInstance()
        lista = findViewById(R.id.recycleViewNovedades)
        buscadorSubastas = findViewById(R.id.buscadorSubastas)
        auth = FirebaseAuth.getInstance()
        rellenarRecycleViex()




        val nombre: String = intent.getStringExtra("nombreUsuarioNovedades")
        tvNombreUsuarioNovedades.text = nombre

        //Buscador de artículos
        buscadorSubastas.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                filtrar(s.toString())
            }

        })

        novedades = ArrayList()


        imagenUsuario1.setOnClickListener {
            infoUsuario()
        }




        botonSubastasPrincipal.setOnClickListener {
         infoSubasta()
        }

        bVender1.setOnClickListener {
            val venderPantalla = Intent(this@PantallaPrincipalNovedades, VenderObjetos::class.java)
            startActivity(venderPantalla)
        }
    }

    private fun filtrar(texto: String) {
        var filtrarLista: ArrayList<ObjetoNovedad> = ArrayList()

        for (n in this!!.novedades!!) {
            if (n.nombre.toLowerCase().contains(texto.toLowerCase())) {
                filtrarLista.add(n)
            }
        }
        adaptador?.filtrarLista(filtrarLista)
    }

    private fun infoUsuario() {
        val correo: String = intent.getStringExtra("correo")
        val contrasena: String = intent.getStringExtra("contraUsuarioNovedades")
        auth.signInWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    vistaNovedades(correo, contrasena)
                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        Toast.makeText(
                            this,
                            "Error de autentificación vuelve a escribir los datos",
                            Toast.LENGTH_LONG
                        )
                    }


                }
            }

    }


    private fun vistaNovedades(correo: String, contra: String) {
        val pos: Int = correo.indexOf("@")
        val user: String = correo.substring(0, pos)
        val intent = Intent(this, PantallaUsuario::class.java)
        intent.putExtra("nombrePerfil", user)
        intent.putExtra("contraPerfil", contra)
        intent.putExtra("correoPerfil",correo)
        startActivity(intent)
    }

    private fun infoSubasta() {
        val correo: String = intent.getStringExtra("correo")
        val contrasena: String = intent.getStringExtra("contraUsuarioNovedades")

        auth.signInWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    vistaSubastas(correo, contrasena)
                } else {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        Toast.makeText(
                            this,
                            "Error de autentificación vuelve a escribir los datos",
                            Toast.LENGTH_LONG
                        )
                    }
                }
            }
    }

    private fun vistaSubastas(correo: String, contra: String){
        val pos: Int = correo.indexOf("@")
        val user: String = correo.substring(0, pos)
        val intent = Intent(this, PantallaPrincipalSubastas::class.java)
        intent.putExtra("nombreSubasta", user)
        intent.putExtra("contraSubasta", contra)
        intent.putExtra("corroSubasta",correo)
        startActivity(intent)
    }

    private fun rellenarRecycleViex(){
        var ref = data!!.getReference("ArticulosEnVentas")

        ref?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dt in dataSnapshot.children) {
                    val prod = dt.getValue(ObjetoNovedad::class.java)
                    Log.i("Datos", dt.value!!.toString())
                    if (prod != null) {
                        novedades!!.add(prod)
                    }
                }
                lista = findViewById(R.id.recycleViewNovedades)
                layoutManager = LinearLayoutManager(this@PantallaPrincipalNovedades)
                adaptador = AdapterNovedades(this@PantallaPrincipalNovedades, novedades!!)
                lista?.layoutManager = layoutManager
                lista?.adapter = adaptador
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.e("errorRecycleView", "error al cargar el recycleView")
            }

        })
    }

}



