package com.example.tradefast

import Adapter.AdaptadorSubastas
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.tradefast.objetos.ObjetoSubasta
import kotlinx.android.synthetic.main.activity_pantalla_principal_subastas.*


class PantallaPrincipalSubastas : AppCompatActivity() {

    var subastas:ArrayList<ObjetoSubasta>?=null
    var layoutManager: RecyclerView.LayoutManager?=null
    var adaptador: AdaptadorSubastas?=null
    var lista: RecyclerView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_subastas)

        val nombre = intent.getStringExtra("nombreSubasta")
        val contra = intent.getStringExtra("contraSubasta")
        val correo = intent.getStringExtra("corroSubasta")

        subastaNombre.text=nombre

       subastas = ArrayList()

        subastas?.add(
            ObjetoSubasta(
                "Sombrero", 3.50, 400.0,
                "Antonio", R.drawable.abc_btn_radio_material
            )
        )

        subastas?.add(
            ObjetoSubasta(
                "Raton", 4.75, 6.98,
                "Pepe", R.drawable.abc_btn_radio_material
            )
        )

        lista=findViewById(R.id.recycleViewSubastas)
        layoutManager = LinearLayoutManager(this)
        adaptador= AdaptadorSubastas(this, subastas!!)
        lista?.layoutManager=layoutManager
        lista?.adapter=adaptador


        imagenUsuario2.setOnClickListener {
            val imagen1 = Intent(this,PantallaUsuario::class.java)
            startActivity(imagen1 ) }

        botonNovedadesSecundario.setOnClickListener {
            val intNovedades = Intent(this, PantallaPrincipalNovedades::class.java)
            startActivity(intNovedades)
        }
    }
}
