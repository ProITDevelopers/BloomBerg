package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_categorias_conteudo.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Noticia

class CategoriasOutrasAdapeter(private val context:Context,private val noticias:List<Noticia >) :
    RecyclerView.Adapter<CategoriasOutrasAdapeter.MyViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_categorias_conteudo,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (noticias.size>=5){
            return 5
        }
        return noticias.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        //val noticias = noticias[posicao]
        holder.mudarDados(/*noticias,*/posicao)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun mudarDados(/*noticias:Noticia?,*/posicao:Int){
            when(posicao){
                0 ->itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria)
                1 ->itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_para_si)
                2 ->itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_opinioes)
                3 ->itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_tecnologia)
                4 ->itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_crypto)
            }
        }
    }
}