package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_oque_assistir_item_sub_back_up.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.communs.logDebug
import proitdevelopers.com.bloomberg.modelo.Noticia

class OqueAssisirItemSubTercAdapeter(
    private val context: Context,
    private val noticias: MutableList<Noticia>,
    private val valorItensRetornados: Int,
    private val noticia: Noticia,
    private val videoView: VideoView,
    private val mediaController: MediaController,
    private val progressBar: ProgressBar
) :
    RecyclerView.Adapter<OqueAssisirItemSubTercAdapeter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_oque_assistir_item_sub_back_up, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        holder.mudarDados(noticia/*,posicao*/)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) /* , View.OnClickListener*/ {
        fun mudarDados(noticia: Noticia?/*,posicao:Int*/) {
            itemView.txt_data_video_noticia.text = noticia?.data
            itemView.txvTitulo_video.text = noticia?.titulo
            itemView.tv_descricao_dec.text = noticia?.descricao
                OqueAssisirItemSubTercQuartAdapet(noticias,itemView.recyclerViewItensReproASeguir,
                    itemView.txvTitulo_video,
                    itemView.tv_descricao_dec,
                    itemView.txt_data_video_noticia,
                    videoView)
        }

    }

    private fun OqueAssisirItemSubTercQuartAdapet(
        noticias: MutableList<Noticia>,
        recyclerView: RecyclerView,
        viewTitulo: TextView,
        viewDescrica: TextView,
        viewData: TextView,
        videoView: VideoView
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfQueAssistir = OqueAssisirItemSubTercQuartAdapeter(context,noticias)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterConfQueAssistir
        adapterConfQueAssistir.setOnItemClickListener(object : OqueAssisirItemSubTercQuartAdapeter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
               viewTitulo.text = noticias[pos].titulo
               viewDescrica.text = noticias[pos].descricao
               viewData.text = noticias[pos].data

                val uri = Uri.parse(noticias[pos].video)
                logDebug("TAG",uri.toString())
                videoView.setVideoURI(uri)
                mediaController.setAnchorView(videoView)
                videoView.setMediaController(mediaController)
                videoView.seekTo(0)
                videoView.start()
                progressBar.visibility = View.VISIBLE
                videoView.setOnInfoListener{player,what,extras ->
                    if(what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                        progressBar.visibility = View.GONE
                    true
                }
           }
       })
    }
}