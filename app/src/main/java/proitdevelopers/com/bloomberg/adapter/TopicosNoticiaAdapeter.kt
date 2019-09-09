package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_lista_fragment_bolsa_um.view.*
import kotlinx.android.synthetic.main.item_lista_topicos_pesquisa.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Bolsa
import proitdevelopers.com.bloomberg.modelo.Topico

class TopicosNoticiaAdapeter(private val context:Context, private val topicos:List<Topico >) : RecyclerView.Adapter<TopicosNoticiaAdapeter.MyViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_topicos_pesquisa,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topicos.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        val topicos = topicos[posicao]
        holder.mudarDados(topicos)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun mudarDados(topicos:Topico?){
            itemView.topico_pesquisa.text = topicos!!.topico
        }
    }

}