package proitdevelopers.com.bloomberg.adapter.swipeNoticiasViewPager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view_pager_noticias.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Noticia

class NavegacaoNoticiasAdapter(val context: Context,val listaNoticias:List<Noticia>):RecyclerView.Adapter<NavegacaoNoticiasAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view_pager_noticias,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = listaNoticias.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val noticia = listaNoticias[position]
        holder.mudarUiConteudo(noticia)
    }


    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun mudarUiConteudo(noticia: Noticia) {

            itemView.view_pager_titulo.text = noticia.titulo
            itemView.view_pager_fonte.text = noticia.fonte
            itemView.view_pager_data_pub.text = noticia.data
            itemView.view_pager_descricao.text = noticia.descricao
            itemView.view_pager_conteudo.text = noticia.conteudo

            Glide.with(context)
                .load(noticia.foto)
                .into(itemView.view_pager_img)

        }
    }
}