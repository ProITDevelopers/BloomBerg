package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_lista_top_news.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.basededados.entitys.Noticia
import proitdevelopers.com.bloomberg.communs.carregarFoto
import proitdevelopers.com.bloomberg.communs.guardarNoticiaOffiline
import proitdevelopers.com.bloomberg.communs.partilharNoticia
import proitdevelopers.com.bloomberg.viewModel.NoticiaViewModel

class CategoriasOutrasAdapterSub(
    private val context: Context,
    private val noticias: MutableList<Noticia>,
    private val qtdRetornados: Int,
    private val verMaisId: Int,
    private val noticiaViewModel: NoticiaViewModel
) : RecyclerView.Adapter
<CategoriasOutrasAdapterSub.MyVilHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVilHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_top_news, parent, false)
        return MyVilHolder(view)
    }

    override fun getItemCount(): Int = if (verMaisId==0 && noticias.size>0) qtdRetornados-1 else qtdRetornados

    override fun onBindViewHolder(holder: MyVilHolder, position: Int) {
        if (verMaisId == 0) {
            if (position+1<noticias.size){
                val noticia = noticias[position+1]
                holder.mudarDados(noticia)
            }
        } else if (verMaisId == 1) {
            if (position + 3 < noticias.size) {
                val noticia = noticias[position + 3]
                holder.mudarDados(noticia)
            }
        }
    }

    inner class MyVilHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun mudarDados(noticia: Noticia) {
            context.carregarFoto(noticia.foto,itemView.item_sub_not_img)
            itemView.item_not_s_cat.text = noticia.categoria
            itemView.item_not_titulo.text = noticia.titulo
            itemView.item_not_s_data.text = noticia.data
            itemView.item_sub_not_ic_partilha.setOnClickListener {
                context.partilharNoticia(noticia.titulo,noticia.descricao,noticia.conteudo)
            }
            itemView.item_not_s_icone_favorito.setOnClickListener {
                context.guardarNoticiaOffiline(noticia)
            }
            click()
        }

        fun click() {

            if (verMaisId==0){
                itemView.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_maisNoticiasFragment_to_detalheNoticiaFragment)
                )
            }else{
                itemView.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detalheNoticiaFragment)
                )
            }
        }

    }
}