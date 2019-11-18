package Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tradefast.CompraNovedades
import com.example.tradefast.ObjetoNovedad
import com.example.tradefast.R
import kotlinx.android.synthetic.main.activity_pantalla_info_novedades.view.*


class AdapterNovedades(private val context: Context, items: ArrayList<ObjetoNovedad>) :
    RecyclerView.Adapter<AdapterNovedades.ViewHolder>() {

    var items: ArrayList<ObjetoNovedad>? = null

    init {
        this.items = items
    }

    var viewHolder: ViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AdapterNovedades.ViewHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.activity_pantalla_info_novedades, parent, false)
        viewHolder = ViewHolder(vista)
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)

        val nombre=item?.nombre
        val precio=item?.precio
        val usuario=item?.usuario
        val foto=item?.foto
        val desp=item?.descripcion


        holder.nombrePrecio?.text = "Nombre del objeto: $nombre \n Precio: $precio"
        holder.usu?.text = "Usuario: $usuario"
        if (foto != null) {
            holder.foto?.setImageResource(foto)
        }
        holder.desp?.text = "Drescripcion:  + $desp"

        holder.vista.setOnClickListener {
            val intent1 = Intent(context, CompraNovedades::class.java)
            intent1.putExtra("nombreNovedadCompra", nombre)
            intent1.putExtra("precioNovedadCompra", precio)
            intent1.putExtra("usuarioNovedadCompra", usuario)
            intent1.putExtra("fotoNovedadCompra", foto)
            intent1.putExtra("despNovedadCompra", desp)
            context.startActivity(intent1)
        }


    }


    override fun getItemCount(): Int {
        return this.items?.count()!!
    }


    class ViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        var vista = vista

        var foto: ImageView? = null
        var nombrePrecio: TextView? = null
        var usu: TextView? = null
        var desp: TextView? = null

        init {
            foto = vista.fotoNovedadUsuario
            nombrePrecio = vista.precioSubasta
            usu = vista.usuarioNovedad
            desp = vista.precioSubasta
        }
    }

}
