package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada.media


import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_reproducao_video.*
import kotlinx.android.synthetic.main.fragment_media_video.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.OqueAssisirItemSubTercAdapeter
import proitdevelopers.com.bloomberg.communs.TAG
import proitdevelopers.com.bloomberg.modelo.Noticia
import proitdevelopers.com.bloomberg.modelo.valoresNoticias

class MediaVideoFragment : Fragment() {

    private var plaayBackPosition = 0
    private val dashUrlTest = "https://firebasestorage.googleapis.com/v0/b/jogo-hoje.appspot.com/o/Basic%20Flamenco%20Techniques.mp4?alt=media&token=75d02dee-3614-4b86-a7b2-8972dcfb3899"
    private lateinit var mediaController: MediaController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_media_video, container, false)

        mediaController = MediaController(view.context)
        view.video_view.setOnPreparedListener{
            mediaController.setAnchorView(video_content)
            view.video_view.setMediaController(mediaController)
            view.video_view.seekTo(plaayBackPosition)
            view.video_view.start()
        }
        view.video_view.setOnInfoListener{player,what,extras ->
            if(what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                progressBar.visibility = View.INVISIBLE
            true
        }

        oqueAssisirItemSubAdapeter(
            valoresNoticias.noticias,view.recyclerViewASeguir,
            view.context,
            valoresNoticias.noticias[0],
            view.video_view,
            mediaController,
            view.progressBar)

        return view
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
