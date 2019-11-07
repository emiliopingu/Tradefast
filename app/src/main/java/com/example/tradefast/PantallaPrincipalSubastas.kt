package com.example.tradefast

import Adapter.AdaptadorSubastas
import Adapter.AdapterNovedades
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_pantalla_principal_novedades.*

class PantallaPrincipalSubastas : AppCompatActivity() {

    var subastas:ArrayList<ObjetoSubasta>?=null
    var layoutManager: RecyclerView.LayoutManager?=null
    var adaptador: AdaptadorSubastas?=null
    var lista: RecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_subastas)

       subastas = ArrayList()

        subastas?.add(ObjetoSubasta("Sombrero",3.50,400.0,
            "Antonio",R.drawable.abc_btn_radio_material))

        subastas?.add(
            ObjetoSubasta("Raton",4.75,6.98,
            "Pepe",R.drawable.abc_btn_radio_material))

        lista=findViewById(R.id.recycleViewSubastas)
        layoutManager = LinearLayoutManager(this)
        adaptador= AdaptadorSubastas(this, subastas!!)
        lista?.layoutManager=layoutManager
        lista?.adapter=adaptador


        imagenUsuario1.setOnClickListener {
            val imagen1 = Intent(this,PantallaUsuario::class.java)
            startActivity(imagen1 ) }

        botonSubastasPrincipal.setOnClickListener {
            val intNovedades = Intent(this,PantallaPrincipalNovedades::class.java)
            startActivity(intNovedades)
        }
    }
}
