package proitdevelopers.com.bloomberg.adapter.noticias_guardadas

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_lista_noticias_salvas.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.basededados.entitys.Noticia
import proitdevelopers.com.bloomberg.communs.carregarFoto

class NoticiasGuardadasAdapeter(private val context:Context,private val onDeleteClickListner: OnDeleteClickListner?) :
    RecyclerView.Adapter<NoticiasGuardadasAdapeter.MyViewHolder>(){

    private var noticiasRoom:List<Noticia> = mutableListOf()

    interface OnDeleteClickListner{
        fun onDeleteClickListner(noticiaFavorita:Noticia)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_noticias_salvas,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = noticiasRoom.size

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        val noticia = noticiasRoom[posicao]
            holder.mudarDados(noticia)
    }

    fun setNoticias(noticias:List<Noticia>){
        noticiasRoom = noticias
        notifyDataSetChanged()

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun mudarDados(noticia: Noticia){
            context.carregarFoto(noticia.foto,itemView.item_sub_not_img)
            itemView.item_not_s_cat.text = noticia.categoria
            itemView.item_not_titulo.text = noticia.titulo
            itemView.item_not_s_data.text = noticia.data

            itemView.item_not_s_icone_favorito.setOnClickListener {
                onDeleteClickListner?.onDeleteClickListner(noticia)
                Toast.makeText(context,"Notícia removida !",Toast.LENGTH_SHORT).show()
            }
        }
    }
}