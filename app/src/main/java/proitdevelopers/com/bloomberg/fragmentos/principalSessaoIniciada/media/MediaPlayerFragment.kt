package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada.media


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_media_player.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.nossosMeios.NossosMeiosAdapter
import proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada.AudioFragment

class MediaPlayerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_media_player, container, false)
            viewPagesMedia(view)
        return view
    }

private fun viewPagesMedia(view: View) {

    val adapter = NossosMeiosAdapter(childFragmentManager)
    adapter.addFragment(MediaVideoFragment(),"Video")
//    adapter.addFragment(MediaAudioFragment(),"Audio")
    adapter.addFragment(AudioFragment(),"Audio")
    view.viewPager_media.adapter = adapter
    view.tabs_media.setupWithViewPager(view.viewPager_media)

}


}
