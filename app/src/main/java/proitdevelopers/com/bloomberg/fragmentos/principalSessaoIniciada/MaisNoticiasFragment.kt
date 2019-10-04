package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_mais_noticias.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.maisNoticias.MaisNoticiasAdapter
import proitdevelopers.com.bloomberg.modelo.Noticia
import proitdevelopers.com.bloomberg.modelo.valoresNoticias

class MaisNoticiasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mais_noticias, container, false)

        configurarMaisNoticias(view.context, valoresNoticias.noticias,view)

        return view
    }

    fun configurarMaisNoticias(contextView:Context,noticias:MutableList<Noticia>,view:View){
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfigMaisNoticias = MaisNoticiasAdapter(contextView,noticias)
        view.recyclerViewMaisNoticias.layoutManager = layoutManager
        view.recyclerViewMaisNoticias.adapter = adapterConfigMaisNoticias

    }

}