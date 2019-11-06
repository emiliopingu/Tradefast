package Adapters

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
/*
class AdapterTareas(private val context: Context, var list: MutableList<Task>) :
    RecyclerView.Adapter<AdapterTareas.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tareas_list, parent, false)
        return viewHolder(view)

    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val l=list[position]
        val nombre =l.name
        val lugar =l.place
        val usuario =l.user
        val descripcion =l.date
        val fecha =l.description

        holder.view.tvNombreTarea.text = nombre
        holder.view.tvLugarTarea.text = lugar
        holder.view.tvUsuarioTarea.text = usuario


      /*  holder.view.setOnClickListener {
            val intent1 = Intent(context, DescripcionTareaActivity::class.java)
            intent1.putExtra("tareaDescripcion", nombre)
            intent1.putExtra("usuarioDescripcion", usuario)
            intent1.putExtra("problemaDescripcion", descripcion)
            intent1.putExtra("lugarDescripcion", lugar)
            intent1.putExtra("fechaDescripcion", fecha)
            context.startActivity(intent1)
        }*/
    }

    override fun getItemCount(): Int {
        return list.size
    }



}
*/