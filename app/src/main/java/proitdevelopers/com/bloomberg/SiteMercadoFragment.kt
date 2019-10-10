package proitdevelopers.com.bloomberg


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_site_mercado.view.*


class SiteMercadoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_site_mercado, container, false)

        view.web_view_mercado.loadUrl("https://mercado.co.ao/")
        view.web_view_mercado.settings.javaScriptEnabled = true
        view.web_view_mercado.webViewClient = WebViewClient()

        return view
    }


}
