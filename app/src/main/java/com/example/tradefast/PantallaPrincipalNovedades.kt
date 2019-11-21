package com.example.tradefast

import Adapter.AdapterNovedades
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pantalla_principal_novedades.*

class PantallaPrincipalNovedades : AppCompatActivity() {


    var novedades: ArrayList<ObjetoNovedad>? = null
    var layoutManager: RecyclerView.LayoutManager? = null


    var adaptador: AdapterNovedades? = null
    var data: FirebaseDatabase? = null
    var lista: RecyclerView? = null


    @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_novedades)

        val user = intent.getStringExtra("user")
        txNombreUsuarioSubastas.text = user

        data = FirebaseDatabase.getInstance()
        lista = findViewById(R.id.recycleViewNovedades)



        novedades = ArrayList()

        var ref= data!!.getReference("ArticulosDeVenta")
        ref?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()){
                    for(x in p0.children){
                        novedades!!.removeAll(novedades!!)
                        val articulo=x.getValue(ObjetoNovedad::class.java)
                        novedades?.add(articulo!!)
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO(reason = "not implemented") //To change body of created functions use File | Settings | File Templates.
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


}
