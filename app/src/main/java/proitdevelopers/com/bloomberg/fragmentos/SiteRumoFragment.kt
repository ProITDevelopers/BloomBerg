package proitdevelopers.com.bloomberg.fragmentos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_site_rumo.view.*
import proitdevelopers.com.bloomberg.R

class SiteRumoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_site_rumo, container, false)

        view.web_view_rumo.loadUrl("https://mediarumo.com/")
        view.web_view_rumo.settings.javaScriptEnabled = true
        view.web_view_rumo.webViewClient = WebViewClient()


        return view
    }


}
