package Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tradefast.objetos.ObjetoSubasta
import com.example.tradefast.R
import kotlinx.android.synthetic.main.activity_pantalla_info_subastas.view.*

class AdaptadorSubastas (private val context: Context, items: ArrayList<ObjetoSubasta>):
RecyclerView.Adapter<AdaptadorSubastas.ViewHolder>() {


    var items:ArrayList<ObjetoSubasta>?=null
    init {
        this.items=items
    }
    var viewHolder:ViewHolder?=null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int):AdaptadorSubastas.ViewHolder  {
        val vista = LayoutInflater.from(context).inflate(R.layout.activity_pantalla_info_subastas, parent, false)
        viewHolder =ViewHolder(vista)
        return viewHolder!!
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= items?.get(position)
        holder. nombrePujaPrimera?.text="Nombre del objeto: "+item?.nombre +"\n" + "Puja Inicial: "+ item?.pujaFinal
        holder.usu?.text="Usuario: "+ item?.usuario
        holder.foto?.setImageResource(item?.foto!!)
        holder. ultimaPuja?.text="Puja final: "+ item?.pujaFinal
    }


    override fun getItemCount(): Int {
        return this.items?.count()!!
    }



    class ViewHolder(vista: View): RecyclerView.ViewHolder(vista){
        var vista=vista

        var foto: ImageView?=null
        var nombrePujaPrimera: TextView?=null
        var usu: TextView?=null
        var ultimaPuja: TextView?=null
        init {
            foto=vista.imagenPuja
            nombrePujaPrimera=vista.pujaInicialNombre
            usu=vista.precioFinalSubasta
            ultimaPuja=vista.ultimaPujaSubasta
        }
    }

}
