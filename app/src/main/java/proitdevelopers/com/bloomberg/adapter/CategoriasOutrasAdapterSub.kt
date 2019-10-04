package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Noticia

class CategoriasOutrasAdapterSub(
    private val context: Context,
    private val recyclerView: RecyclerView,
    private val noticias: MutableList<Noticia>,
    private val qtdRetornados: Int
):RecyclerView.Adapter
    <CategoriasOutrasAdapterSub.MyVilHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVilHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_top_news,parent,false)
        return MyVilHolder(view)
    }

    override fun getItemCount(): Int = qtdRetornados

    override fun onBindViewHolder(holder: MyVilHolder, position: Int) {

    }

    inner class MyVilHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        fun mudarDados(){

        }

    }
}