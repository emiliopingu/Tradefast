package com.example.tradefast

import Adapter.AdapterNovedades
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pantalla_principal_novedades.*
import com.example.tradefast.objetos.ObjetoNovedad

class PantallaPrincipalNovedades : AppCompatActivity() {


    var novedades: ArrayList<ObjetoNovedad>? = null
    var layoutManager: RecyclerView.LayoutManager? = null


    var adaptador: AdapterNovedades? = null
    var data: FirebaseDatabase? = null
    var lista: RecyclerView? = null
    private lateinit var buscadorSubastas: EditText


    @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_novedades)

        val user = intent.getStringExtra("user")
        txNombreUsuarioSubastas.text = user

        data = FirebaseDatabase.getInstance()
        lista = findViewById(R.id.recycleViewNovedades)
        buscadorSubastas = findViewById(R.id.buscadorSubastas)

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

        imagenUsuario1.setOnClickListener {
            val imagen1 = Intent(this, PantallaUsuario::class.java)
            startActivity(imagen1)
        }

        botonSubastasPrincipal.setOnClickListener {
            val intSubastas = Intent(this, PantallaPrincipalSubastas::class.java)
            startActivity(intSubastas)
        }

        bVender1.setOnClickListener {
            val venderPantalla = Intent(this@PantallaPrincipalNovedades, VenderObjetos::class.java)
            startActivity(venderPantalla)
        }
    }

    private fun filtrar(texto:String) {
        var filtrarLista:ArrayList<ObjetoNovedad> = ArrayList()


        for (n in this!!.novedades!!) {
            if (n.nombre.toLowerCase().contains(texto.toLowerCase())) {
                filtrarLista.add(n)
            }
        }
        adaptador?.filtrarLista(filtrarLista)
    }
}
