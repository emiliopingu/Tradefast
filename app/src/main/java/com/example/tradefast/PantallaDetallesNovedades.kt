package com.example.tradefast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tradefast.objetos.ObjetoNovedad
import kotlinx.android.synthetic.main.activity_pantalla_detalles_novedades.*

class PantallaDetallesNovedades : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_detalles_novedades)
         val producto = intent.getSerializableExtra("producto") as ObjetoNovedad

        val precio = producto.precio.toString()
        val correo = producto.correoUsuario
        val nombre = producto.nombre
        val description = producto.descripcion
        val foto = producto.foto

        infoNovedad.text="  \n \n Usuario : $correo \n \n  Artículo : $nombre \n \n  Precio :  $precio €  \n \n Descripcion : $description"
        imagenInfoNovedad.setImageResource(foto)



    }
}
