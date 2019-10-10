package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_detalhe_noticia.*
import kotlinx.android.synthetic.main.fragment_detalhe_noticia.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.swipeNoticiasViewPager.NavegacaoNoticiasAdapter
import proitdevelopers.com.bloomberg.modelo.Noticia
import proitdevelopers.com.bloomberg.modelo.valoresNoticias

class DetalheNoticiaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_detalhe_noticia, container, false)

        onClick(view)

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

    private fun onClick(view: View){
        view.frag_detalhe_btn_fav.setOnClickListener {
            Toast.makeText(view.context,"Favorito", Toast.LENGTH_SHORT).show()
        }

        view.frag_detalhe_btn_part.setOnClickListener {
            Toast.makeText(view.context,"Partilha", Toast.LENGTH_SHORT).show()
        }

        view.frag_detalhe_btn_voltar.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_detalheNoticiaFragment_to_maisNoticiasFragment)
        )
    }


}
