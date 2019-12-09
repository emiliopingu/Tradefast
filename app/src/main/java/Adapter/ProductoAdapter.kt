package Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.tradefast.R
import com.example.tradefast.objetos.ObjetoNovedad
import kotlinx.android.synthetic.main.info_novedades.view.*

class ProductoAdapter(private val c: Context, private val list: ArrayList<ObjetoNovedad>) :
    ArrayAdapter<ObjetoNovedad>(c, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(c).inflate(R.layout.info_novedades, parent, false)

        val producto = list[position]
        val precio = producto.precio.toString()
        val correo = producto.correoUsuario
        val nombre = producto.nombre
        val description = producto.descripcion
        val foto =0
        val vendido=producto.vendido
        val comprador=producto.compradoPor
        val id=producto.id
        layout.datosNovedades.text=" \n Usuario : $correo \n Artículo : $nombre \n Precio :  $precio € "
        layout.fotoNovedad.setImageResource(0)


        return layout
    }

}