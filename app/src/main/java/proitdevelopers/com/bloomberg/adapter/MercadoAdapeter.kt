package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_lista_top_news.view.imgSeparador
import kotlinx.android.synthetic.main.item_lista_trading.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.communs.esconderSeparador
import proitdevelopers.com.bloomberg.modelo.Noticia

class MercadoAdapeter(val context:Context, val noticiasL:List<Noticia >) : RecyclerView.Adapter<MercadoAdapeter.MyViewHolder>(){

    var limite = 0

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_trading,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (noticiasL.size>=8){
            limite = 8
            return 8
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
            itemView.item_tendencias_categoria.text = noticias!!.categoria
            itemView.item_tendencias_titulo.text = noticias.titulo
            itemView.trading_lugar_tv.text = (posicao+1).toString()
            itemView.item_tendencias_data_pub.text = noticias.data
            esconderSeparador(itemView.imgSeparador,posicao,limite)

        }
    }

}