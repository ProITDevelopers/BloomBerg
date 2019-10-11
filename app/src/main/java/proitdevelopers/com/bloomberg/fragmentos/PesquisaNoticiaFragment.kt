package proitdevelopers.com.bloomberg.fragmentos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import proitdevelopers.com.bloomberg.R

class PesquisaNoticiaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_pesquisa_noticia, container, false)
    }

}
