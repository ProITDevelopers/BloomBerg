package proitdevelopers.com.bloomberg.adapter.maisNoticias

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_lista_top_news.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Noticia

class MaisNoticiasAdapter(val context: Context,val noticias:List<Noticia>):
    RecyclerView.Adapter<MaisNoticiasAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_mais_noticias,parent,false)
        view.imgSeparador.visibility = View.GONE
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = noticias.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

}