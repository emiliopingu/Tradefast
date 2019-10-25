package com.example.tradefast

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botonRegistrarse.setOnClickListener {
            val intRegistrar = Intent(this,PantallaRegistro::class.java)
            startActivity(intRegistrar)
        }

        botonIniciarSecion.setOnClickListener {
            val intIniciarSecion = Intent(this,PantallaPrincipalNovedades::class.java)
            startActivity(intIniciarSecion)
        }
    }


}
