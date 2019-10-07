package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_lista_top_news.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Noticia

class CategoriasOutrasAdapterSub(
    private val context: Context,
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
            try {
                val noticia = noticias[position+3]
                holder.mudarDados(noticia)
            }finally {

            }
    }

    inner class MyVilHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        fun mudarDados(noticia: Noticia){
            Glide.with(context)
                .load(noticia.foto)
                .into(itemView.item_sub_not_img)
            itemView.item_tendencias_categoria.text = noticia.categoria
            itemView.item_tendencias_titulo.text = noticia.titulo
            itemView.item_tendencias_data_pub.text = noticia.data
            itemView.item_sub_not_ic_partilha.setOnClickListener { Toast.makeText(context, "Partilhar", Toast.LENGTH_SHORT).show() }
            itemView.item_sub_not_ic_favorito.setOnClickListener { Toast.makeText(context, "Favoritos", Toast.LENGTH_SHORT).show() }
            click()
        }

        fun click(){
            itemView.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detalheNoticiaFragment)
            )
        }

    }
}