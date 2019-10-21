package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_detalhe_noticia.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.swipeNoticiasViewPager.NavegacaoNoticiasAdapter
import proitdevelopers.com.bloomberg.basededados.entitys.Noticia
import proitdevelopers.com.bloomberg.communs.noticiaViewModel
import proitdevelopers.com.bloomberg.communs.partilharNoticia
import proitdevelopers.com.bloomberg.modelo.valoresNoticias
import proitdevelopers.com.bloomberg.viewModel.NoticiaViewModel
import java.util.*

class DetalheNoticiaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_detalhe_noticia, container, false)
        noticiaViewModel = ViewModelProviders.of(this).get(NoticiaViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarViewPager(view.context, valoresNoticias.noticias)
    }

    fun configurarViewPager(context: Context, noticias: List<Noticia>) {
        val adapter = NavegacaoNoticiasAdapter(context, noticias)
        viewPagerNoticias.adapter = adapter
        viewPagerNoticias.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPagerNoticias.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                onClick(noticias[position])
            }
        })
    }

    private fun onClick(noticia:Noticia) {
        frag_detalhe_btn_fav.setOnClickListener {
            noticia.id = UUID.randomUUID().toString()
            noticiaViewModel.insertNoticiasFavoritas(noticia)
            Toast.makeText(context, "Noticia guardada nos favoritos", Toast.LENGTH_SHORT).show()
        }

        frag_detalhe_btn_part.setOnClickListener {
            context!!.partilharNoticia(noticia.titulo,noticia.descricao,noticia.conteudo)
        }

        frag_detalhe_btn_voltar.setOnClickListener {
            findNavController().navigate(DetalheNoticiaFragmentDirections.actionDetalheNoticiaFragmentToHomeFragment())
        }
    }


}
