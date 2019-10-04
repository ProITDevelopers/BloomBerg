package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_lista_top_news.view.*
import proitdevelopers.com.bloomberg.communs.esconderSeparador
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Noticia

class TopNewsAdapeter(val context:Context, val noticiasL:List<Noticia >) : RecyclerView.Adapter<TopNewsAdapeter.MyViewHolder>(){

    var limite: Int = 0

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_top_news,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (noticiasL.size>=7){
            limite = 7
            return 7
        }
        limite = noticiasL.size
        return noticiasL.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        val noticias = noticiasL[posicao]
        holder.mudarDados(noticias,posicao)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun mudarDados(noticias:Noticia?,posicao:Int){
            itemView.apply {
                trading_categoria_tv.text = noticias!!.categoria
                trading_titulo_tv.text = noticias.titulo
                trading_data_tv.text = noticias.data
            }
            esconderSeparador(itemView.imgSeparador,posicao,limite)
        }
    }

}