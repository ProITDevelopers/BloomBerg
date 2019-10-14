package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_oque_assistir.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.activitys.ReproducaoVideoActivity
import proitdevelopers.com.bloomberg.communs.partilharNoticia
import proitdevelopers.com.bloomberg.communs.trocarActivityComNoticia
import proitdevelopers.com.bloomberg.modelo.Noticia

class OqueAssisirAdapeter(private val context:Context, private val noticias:List<Noticia >) :
    RecyclerView.Adapter<OqueAssisirAdapeter.MyViewHolder>(){

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener:ClickListener){
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_oque_assistir,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
            return 1
    }

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        val noticias = noticias[posicao]
        holder.mudarDados(noticias/*,posicao*/)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        fun mudarDados(noticia:Noticia?/*,posicao:Int*/){
            Glide.with(context)
                .load(noticia?.foto)
                .into(itemView.img_video)
            itemView.txt_video_duracao.text = noticia?.duracao
            itemView.txt_noticia.text = noticia?.titulo
            itemView.txtCategoria.text = noticia?.categoria
            itemView.txtData.text = noticia?.data
            oqueAssisirItemSubAdapeter(noticias,itemView.recyclerViewItensAssistir)
            onClickItem(noticia!!)
        }

        fun onClickItem(noticia:Noticia){
            itemView.ic_partilha_gosto.setOnClickListener {
                Toast.makeText(context,"Gosto",Toast.LENGTH_SHORT).show()
            }

            itemView.ic_partilha_destaque.setOnClickListener {
                context.partilharNoticia(noticia.titulo,noticia.descricao,noticia.video)
            }
        }

        override fun onClick(view: View?) {
            mClickListener.onClick(adapterPosition,view!!)
        }

        init {
            itemView.setOnClickListener(this)
        }

    }

    private fun oqueAssisirItemSubAdapeter(noticias: List<Noticia>,recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfQueAssistir = OqueAssisirItemSubAdapeter(context, noticias,2,noticias[0])
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterConfQueAssistir
        adapterConfQueAssistir.setOnItemClickListener(object : OqueAssisirItemSubAdapeter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                context.trocarActivityComNoticia(ReproducaoVideoActivity(),
                    noticias[pos].titulo,noticias[pos].descricao,noticias[pos].conteudo,noticias[pos].foto,
                    noticias[pos].video,noticias[pos].data,noticias[pos].fonte,
                    noticias[pos].categoria,noticias[pos].duracao)
            }
        })
    }
}