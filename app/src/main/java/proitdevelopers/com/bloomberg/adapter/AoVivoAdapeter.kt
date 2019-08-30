package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Noticia

class AoVivoAdapeter(private val context:Context, private val noticias:List<Noticia >) :
    RecyclerView.Adapter<AoVivoAdapeter.MyViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ao_vivo,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        //val noticias = noticias[posicao]
        holder.mudarDados(/*noticias,*/)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun mudarDados(/*noticias:Noticia?,posicao:Int*/){
        }
    }
}