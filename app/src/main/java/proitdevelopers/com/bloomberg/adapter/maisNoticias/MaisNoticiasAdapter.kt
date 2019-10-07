package proitdevelopers.com.bloomberg.adapter.maisNoticias

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_lista_top_news.view.*
import kotlinx.android.synthetic.main.item_mais_noticias.view.*
import kotlinx.android.synthetic.main.item_noticias_lado_a_lado.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.CategoriasOutrasAdapterSub
import proitdevelopers.com.bloomberg.modelo.Noticia

class MaisNoticiasAdapter(val context: Context, val noticias: List<Noticia>) :
    RecyclerView.Adapter<MaisNoticiasAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_mais_noticias, parent, false)
        //view.imgSeparador.visibility = View.GONE
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mudarNoticiaUI()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun mudarNoticiaUI() {
            click()
            if (0<noticias.size){
                Glide.with(context)
                    .load(noticias[0].foto)
                    .into(itemView.mais_not_img_dest)
                itemView.mais_not_titulo_dest.text = noticias[0].titulo
                itemView.mais_not_data_dest.text = noticias[0].data
                itemView.mais_not_partilha_dest.setOnClickListener { Toast.makeText(context, "Partilhar", Toast.LENGTH_SHORT).show() }
                itemView.mais_not_favorito_dest.setOnClickListener { Toast.makeText(context, "Favoritos", Toast.LENGTH_SHORT).show() }
            }
            OqueAssisirItemSubTercQuartAdapet(noticias as MutableList<Noticia>,itemView.recyclerViewMaisNoticia,noticias.size)
        }

        fun click(){
            itemView.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_maisNoticiasFragment_to_detalheNoticiaFragment)
            )
        }
    }

    private fun OqueAssisirItemSubTercQuartAdapet(
        noticias: MutableList<Noticia>,
        recyclerView: RecyclerView,
        qtdRetornados: Int
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfQueAssistir = CategoriasOutrasAdapterSub(context,noticias,qtdRetornados,0)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterConfQueAssistir
    }

}