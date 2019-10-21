package proitdevelopers.com.bloomberg.activitys

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_reproducao_video.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.OqueAssisirItemSubTercAdapeter
import proitdevelopers.com.bloomberg.basededados.entitys.Noticia
import proitdevelopers.com.bloomberg.communs.*
import proitdevelopers.com.bloomberg.modelo.valoresNoticias.noticias

class ReproducaoVideoActivity : AppCompatActivity(){

    private var plaayBackPosition = 0
    private val dashUrlTest = "https://firebasestorage.googleapis.com/v0/b/jogo-hoje.appspot.com/o/Basic%20Flamenco%20Techniques.mp4?alt=media&token=75d02dee-3614-4b86-a7b2-8972dcfb3899"
    private lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproducao_video)

        TAG = "ReproducaoVideoActivityDebug"

        mediaController = MediaController(this)
        video_view.setOnPreparedListener{
            mediaController.setAnchorView(video_content)
            video_view.setMediaController(mediaController)
            video_view.seekTo(plaayBackPosition)
            video_view.start()
        }
        video_view.setOnInfoListener{player,what,extras ->
            if(what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                progressBar.visibility = View.INVISIBLE
            true
        }


        val bundle:Bundle? = intent.extras
        val noticia_titulo = bundle!!.getString(TITULO)
        val noticia_descricao= bundle.getString(DESCRICAO)
        val noticia_conteudo= bundle.getString(CONTEUDO)
        val noticia_foto= bundle.getString(FOTO)
        val noticia_video= bundle.getString(VIDEO)
        val noticia_data= bundle.getString(DATA)
        val noticia_fonte= bundle.getString(FONTE)
        val noticia_Categoria= bundle.getString(CATEGORIA)
        val noticia_duracao= bundle.getString(DURACAO)

        val noticia = Noticia(
            "1",
            noticia_titulo!!.toString(),
            noticia_descricao!!.toString(),
            noticia_conteudo!!.toString(),
            noticia_foto!!.toString(),
            noticia_video!!.toString(),
            noticia_data!!.toString(),noticia_fonte!!.toString(),
            noticia_Categoria!!.toString(),noticia_duracao!!.toString())

        oqueAssisirItemSubAdapeter(noticias,recyclerViewASeguir,
            this@ReproducaoVideoActivity,
            noticia,video_view,
            mediaController,
            progressBar)

    }

    private fun oqueAssisirItemSubAdapeter(
        noticias: MutableList<Noticia>,
        recyclerViewASeguir: RecyclerView,
        context: Context,
        noticia: Noticia,
        videoView: VideoView,
        mediaController: MediaController,
        progressBar: ProgressBar
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfQueAssistir = OqueAssisirItemSubTercAdapeter(
            context,
            noticias,
            noticias.size,
            noticia,videoView,
            mediaController,
            progressBar)
        recyclerViewASeguir.layoutManager = layoutManager
        recyclerViewASeguir.adapter = adapterConfQueAssistir
    }

    override fun onStart() {
        super.onStart()
        val uri = Uri.parse(dashUrlTest)
        video_view.setVideoURI(uri)
        progressBar.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        video_view.pause()
        plaayBackPosition = video_view.currentPosition
    }

    override fun onStop() {
        video_view.stopPlayback()
        super.onStop()
    }
}