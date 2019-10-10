package proitdevelopers.com.bloomberg


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_site_rumo.view.*
import kotlinx.android.synthetic.main.fragment_site_vanguarda.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SiteVanguardaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_site_vanguarda, container, false)
        view.web_view_vanguarda.loadUrl("https://vanguarda.co.ao/")
        view.web_view_vanguarda.settings.javaScriptEnabled = true
        view.web_view_vanguarda.webViewClient = WebViewClient()
        return view
    }


}
