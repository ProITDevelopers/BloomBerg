package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_oque_assistir_item_sub.view.*
import kotlinx.android.synthetic.main.item_oque_assistir_item_sub.view.img_noticia_video
import kotlinx.android.synthetic.main.item_oque_assistir_item_sub.view.txt_data_video
import kotlinx.android.synthetic.main.item_oque_assistir_item_sub.view.txt_duracao_video
import kotlinx.android.synthetic.main.item_oque_assistir_item_sub.view.txt_titulo
import kotlinx.android.synthetic.main.item_oque_assistir_item_sub_.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Noticia

class OqueAssisirItemSubAdapeter(
    private val context: Context,
    private val noticias: List<Noticia>,
    private val valorItensRetornados: Int,
    private val noticia:Noticia
) :
    RecyclerView.Adapter<OqueAssisirItemSubAdapeter.MyViewHolder>() {

    lateinit var mClickListener: ClickListener
    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_oque_assistir_item_sub, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        when (valorItensRetornados){
            2 -> return valorItensRetornados
            else -> return valorItensRetornados
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        val noticias = noticias[posicao]
        holder.mudarDados(noticias/*,posicao*/)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        fun mudarDados(noticias: Noticia?/*,posicao:Int*/) {
            itemView.txt_duracao_video.text = noticias?.duracao
            Glide.with(context)
                .load(noticias?.foto)
                .into(itemView.img_noticia_video)
            itemView.txt_titulo.text = noticias?.titulo
            itemView.txt_data_video.text = noticias?.data
        }

        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}