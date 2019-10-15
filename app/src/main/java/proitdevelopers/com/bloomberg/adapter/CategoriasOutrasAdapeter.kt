package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_categorias_conteudo.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.communs.partilharNoticia
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
            6 -> return 1
            else ->{
                return 0
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        if (identificador == 0)
            holder.mudarDados(0,posicao)
        else if(identificador == 1)
            holder.mudarDados(1,posicao)
        else if(identificador == 2)
            holder.mudarDados(2,posicao)
        else if(identificador == 3)
            holder.mudarDados(3,posicao)
        else if (identificador == 4)
            holder.mudarDados(4,posicao)
        else if (identificador == 5)
            holder.mudarDados(5,posicao)
        else if (identificador == 6)
            holder.mudarDados(6,posicao)
        else
            holder.mudarDados(posicao,posicao)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun mudarDados(posicao:Int,indexLista:Int){
            when(posicao){
                0 ->{
                    itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_atualidade)
                    substituirDadosUI(noticias,indexLista)
                }
                1 ->{
                    itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_nossos_meios_mercado)
                    substituirDadosUI(noticias,indexLista)
                }
                2 ->{
                    itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_para_si)
                    substituirDadosUI(noticias,indexLista)
                }
                3 ->{
                    itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_opniao)
                    substituirDadosUI(noticias,indexLista)
                }
                4 ->{
                    itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_categoria_cultura)
                    substituirDadosUI(noticias,indexLista)
                }
                5 ->{
                    itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_business)
                    substituirDadosUI(noticias,indexLista)
                }
                6 ->{
                    itemView.cat_cont_categoria_txt.text = context.getString(R.string.txt_bolsa)
                    substituirDadosUI(noticias,indexLista)
                }
            }
            OqueAssisirItemSubTercQuartAdapet(noticias,itemView.recyclerViewItensNoticias,qtdRetornados)
        }

        fun substituirDadosUI(noticias: MutableList<Noticia>,indexList:Int){
            if (noticias.size >2){
                Glide.with(context)
                    .load(noticias[indexList].foto)
                    .into(itemView.cat_cont_imagem_noticia)
                itemView.cat_cont_categoria_sub.text = noticias[indexList].categoria
                itemView.cat_cont_noticia_sub.text = noticias[indexList].titulo
                itemView.cat_cont_data_pub.text = noticias[indexList].data

                itemView.cat_cont_cat_1.text = noticias[indexList+1].categoria
                itemView.cat_cont_titulo_1.text = noticias[indexList+1].titulo
                itemView.cat_cont_font_1.text = noticias[indexList+1].data

                itemView.cat_cont_cat_2.text = noticias[indexList+2].categoria
                itemView.cat_cont_titulo_2.text = noticias[indexList+2].titulo
                itemView.cat_cont_font_2.text = noticias[indexList+2].data

                clickMaisNoticias(noticias,indexList)
            }else{
                //Esconder cars media Rumo Noticia
            }
        }

        fun clickMaisNoticias(noticias: MutableList<Noticia>,indexList:Int){
            itemView.btnMaisNoticias.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_maisNoticiasFragment)
            )
            itemView.setOnClickListener ( Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detalheNoticiaFragment) )
            itemView.ic_partilhar_noticia.setOnClickListener {
                context.partilharNoticia(noticias[0].titulo,noticias[0].descricao,noticias[0].conteudo)
            }
            itemView.ic_favoritas_noticia.setOnClickListener { Toast.makeText(context, "Favoritos", Toast.LENGTH_SHORT).show() }
            itemView.ic_partilha_no_card_1.setOnClickListener {
                context.partilharNoticia(noticias[1].titulo,noticias[1].descricao,noticias[1].conteudo)
            }
            itemView.ic_partilha_no_card_2.setOnClickListener {
                context.partilharNoticia(noticias[2].titulo,noticias[2].descricao,noticias[2].conteudo)
            }
            itemView.ic_fav_no_card_1.setOnClickListener { Toast.makeText(context, "Favoritos", Toast.LENGTH_SHORT).show() }
            itemView.ic_fav_no_card_2.setOnClickListener { Toast.makeText(context, "Favoritos", Toast.LENGTH_SHORT).show() }
        }
    }

    private fun OqueAssisirItemSubTercQuartAdapet(
        noticias: MutableList<Noticia>,
        recyclerView: RecyclerView,
        qtdRetornados: Int
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfQueAssistir = CategoriasOutrasAdapterSub(context,noticias,qtdRetornados,1)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterConfQueAssistir
    }

}