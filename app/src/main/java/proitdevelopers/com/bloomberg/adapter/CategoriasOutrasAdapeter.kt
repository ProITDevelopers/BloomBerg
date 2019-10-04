package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_categorias_conteudo.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Noticia

class CategoriasOutrasAdapeter(private val context:Context,
                               private val noticias:MutableList<Noticia>,
                               private val identificador:Int,
                               private val qtdRetornados:Int) :
    RecyclerView.Adapter<CategoriasOutrasAdapeter.MyViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_categorias_conteudo,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        when(identificador){
            0 -> return 1
            1 -> return 1
            2 -> return 1
            3 -> return 1
            4 -> return 1
            5 -> return 1
            else ->{
                return 0
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        //val noticias = noticias[posicao]
        if (identificador == 0)
            holder.mudarDados(/*noticias,*/0)
        else if(identificador == 1)
            holder.mudarDados(/*noticias,*/1)
        else if(identificador == 2)
            holder.mudarDados(/*noticias,*/2)
        else if(identificador == 3)
            holder.mudarDados(/*noticias,*/3)
        else if (identificador == 4)
            holder.mudarDados(/*noticias,*/4)
        else
            holder.mudarDados(/*noticias,*/posicao)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun mudarDados(/*noticias:Noticia?,*/posicao:Int){
            when(posicao){
                0 ->itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_destaque)
                1 ->itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_internacional)
                2 ->itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_politica)
                3 ->itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_sociedade)
                4 ->itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_opniao)
                5 ->itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_manchete)
                6 ->itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_renda_fixa)
            }
            clickMaisNoticias()
            OqueAssisirItemSubTercQuartAdapet(noticias,itemView.recyclerViewItensNoticias,qtdRetornados)
        }

        fun clickMaisNoticias(){
            itemView.btnMaisNoticias.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_maisNoticiasFragment)
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
        val adapterConfQueAssistir = CategoriasOutrasAdapterSub(context,recyclerView,noticias,qtdRetornados)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterConfQueAssistir
    }

}