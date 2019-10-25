package com.example.tradefast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pantalla_registro.*

class PantallaRegistro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registro)

        botonDeRegsitro1.setOnClickListener {
            val intre = Intent(this,MainActivity::class.java)
            startActivity(intre)
        }
    }
}
