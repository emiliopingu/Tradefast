package com.example.tradefast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pantalla_principal_novedades.*

class PantallaPrincipalSubastas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_subastas)


        botonSubastasPrincipal.setOnClickListener {
            val intNovedades = Intent(this,PantallaPrincipalNovedades::class.java)
            startActivity(intNovedades)
        }
    }
}
