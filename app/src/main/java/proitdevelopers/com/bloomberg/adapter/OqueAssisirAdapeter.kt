package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_oque_assistir.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Noticia

class OqueAssisirAdapeter(private val context:Context, private val noticias:List<Noticia >) :
    RecyclerView.Adapter<OqueAssisirAdapeter.MyViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_oque_assistir,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
            return 1
    }

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        //val noticias = noticias[posicao]
        holder.mudarDados(/*noticias,posicao*/)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun mudarDados(/*noticias:Noticia?,posicao:Int*/){
            oqueAssisirItemSubAdapeter(noticias,itemView.recyclerViewItensAssistir)
        }
    }

    private fun oqueAssisirItemSubAdapeter(noticias: List<Noticia>,recyclerView:RecyclerView) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfQueAssistir = OqueAssisirItemSubAdapeter(context, noticias)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterConfQueAssistir
    }
}