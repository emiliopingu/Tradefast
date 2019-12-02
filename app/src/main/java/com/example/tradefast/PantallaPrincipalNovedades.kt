package com.example.tradefast

import Adapter.AdapterNovedades
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
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
import kotlinx.android.synthetic.main.activity_compra_novedades.*
import kotlinx.android.synthetic.main.activity_main.*

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

        var ref = data!!.getReference("ArticulosDeVenta")
        ref?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    for (x in p0.children) {
                        novedades!!.removeAll(novedades!!)
                        val articulo = x.getValue(ObjetoNovedad::class.java)
                        novedades?.add(articulo!!)
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("errorRecycleView", "error al cargar el recycleView")
            }

        })
        imagenUsuario1.setOnClickListener {
           loginUsuario()
        }

        novedades?.add(
            ObjetoNovedad(
                "Sombrero", 3.50, "Sombrero negro y de copa",
                "Antonio", R.drawable.abc_btn_radio_material
            )
        )

        novedades?.add(
            ObjetoNovedad(
                "Raton", 4.75, "Esta vivo",
                "Pepe", R.drawable.abc_btn_radio_material
            )
        )

        lista = findViewById(R.id.recycleViewNovedades)
        layoutManager = LinearLayoutManager(this)
        adaptador = AdapterNovedades(this, novedades!!)
        lista?.layoutManager = layoutManager
        lista?.adapter = adaptador


        botonSubastasPrincipal.setOnClickListener {
            val intSubastas = Intent(this, PantallaPrincipalSubastas::class.java)
            startActivity(intSubastas)
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

    private fun loginUsuario() {
        val correo: String = intent.getStringExtra("correo")
        val contrasena: String = intent.getStringExtra("contraUsuarioNovedades")
                auth.signInWithEmailAndPassword(correo, contrasena)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            vistaNovedades(correo,contrasena)
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
    private fun vistaNovedades(u:String,contra:String) {
        val pos: Int = u.indexOf("@")
        val user: String = u.substring(0, pos)
        val intent = Intent(this, PantallaUsuario::class.java)
        intent.putExtra("nombrePerfil", user)
        intent.putExtra("contraPerfil", contra)
        startActivity(intent)

    }
    }



