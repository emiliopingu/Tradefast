package com.example.tradefast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pantalla_principal_novedades.*

class PantallaPrincipalNovedades : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_novedades)

        botonSubastasPrincipal.setOnClickListener {
            val intSubastas = Intent(this,PantallaPrincipalSubastas::class.java)
            startActivity(intSubastas)
        }
    }
}
