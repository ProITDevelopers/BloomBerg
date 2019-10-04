package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_detalhe_noticia.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.swipeNoticiasViewPager.NavegacaoNoticiasAdapter
import proitdevelopers.com.bloomberg.modelo.Noticia
import proitdevelopers.com.bloomberg.modelo.valoresNoticias

class DetalheNoticiaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_detalhe_noticia, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarViewPager(view.context, valoresNoticias.noticias)
    }

    fun configurarViewPager(context: Context,noticias:List<Noticia>){
        val adapter = NavegacaoNoticiasAdapter(context,noticias)
        viewPagerNoticias.adapter = adapter
        viewPagerNoticias.orientation = ViewPager2.ORIENTATION_HORIZONTAL

    }


}