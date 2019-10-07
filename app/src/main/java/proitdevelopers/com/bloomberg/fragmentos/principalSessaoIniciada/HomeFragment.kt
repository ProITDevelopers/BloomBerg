package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.activitys.ReproducaoVideoActivity
import proitdevelopers.com.bloomberg.adapter.*
import proitdevelopers.com.bloomberg.communs.TAG
import proitdevelopers.com.bloomberg.communs.gerarNumRand
import proitdevelopers.com.bloomberg.communs.trocarActivityComNoticia
import proitdevelopers.com.bloomberg.modelo.Noticia
import proitdevelopers.com.bloomberg.modelo.valoresNoticias

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View
        view = inflater.inflate(R.layout.fragment_home, container, false)

        //configurarViewPagerBolsas(view)
        configurarDestaques(valoresNoticias.noticias,view.context,view)
        configurarMercado(valoresNoticias.noticias,view.context,view)
        configurarCategInternacional(valoresNoticias.noticias,view.context,view)
        configurarCategPolitica(valoresNoticias.noticias,view.context,view)
        configurarCategAoVivo(valoresNoticias.noticias,view.context,view)
        configurarCategOpniao(valoresNoticias.noticias,view.context,view)
        configurarCategOQueAssistir(valoresNoticias.noticias,view.context,view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TAG = "HomeFragmentDebug"
        img_destaque.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detalheNoticiaFragment)
        )
        //view pager das bolsas
        configurarViewPagerBolsas()
                //Travis Scott - HIGHEST IN THE ROOM
    }

    private fun configurarViewPagerBolsas() {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(BolsaFragment())
        adapter.addFragment(BolsaDoisFragment())
        adapter.addFragment(BolsaTresFragment())
        viewPager.adapter = adapter
        worm_dots_indicator.setViewPager(viewPager)
    }

    private fun configurarDestaques(
        noticias: MutableList<Noticia>,
        context: Context,
        view: View
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfDestaque = CategoriasOutrasAdapeter(context,
            noticias,0,gerarNumRand(0,noticias.size))
        view.recyclerDestaque.layoutManager = layoutManager
        view.recyclerDestaque.adapter = adapterConfDestaque
    }

    private fun configurarMercado(
        noticias: List<Noticia>,
        context: Context,
        view: View
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfMercado = MercadoAdapeter(context, noticias)
        view.recyclerMercado.layoutManager = layoutManager
        view.recyclerMercado.adapter = adapterConfMercado
    }

    private fun configurarCategInternacional(
        noticias: MutableList<Noticia>,
        context: Context,
        view: View
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfInternacional = CategoriasOutrasAdapeter(context,
            noticias,1,gerarNumRand(0,noticias.size))
        view.recyclerViewCatInternacional.layoutManager = layoutManager
        view.recyclerViewCatInternacional.adapter = adapterConfInternacional
    }

    private fun configurarCategPolitica(
        noticias: MutableList<Noticia>,
        context: Context,
        view: View
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfPolitica = CategoriasOutrasAdapeter(context,
            noticias,2,gerarNumRand(0,noticias.size))
        view.recyclerViewPolitica.layoutManager = layoutManager
        view.recyclerViewPolitica.adapter = adapterConfPolitica
    }

    private fun configurarCategAoVivo(
        noticias: MutableList<Noticia>,
        context: Context,
        view: View
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        //val adapterConfAoVivo = AoVivoAdapeter(context, noticias)
        val adapterConfSociedade = CategoriasOutrasAdapeter(context,
            noticias,3,gerarNumRand(0,noticias.size))
        view.recyclerViewSociedade.layoutManager = layoutManager
        view.recyclerViewSociedade.adapter = adapterConfSociedade
    }

    private fun configurarCategOpniao(
        noticias: MutableList<Noticia>,
        context: Context,
        view: View
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfCrypto = CategoriasOutrasAdapeter(context,
            noticias,4,gerarNumRand(0,noticias.size))
        view.recyclerViewOpniao.layoutManager = layoutManager
        view.recyclerViewOpniao.adapter = adapterConfCrypto
    }

    private fun configurarCategOQueAssistir(
        noticias: MutableList<Noticia>,
        context: Context,
        view: View
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterOqueAssistir = OqueAssisirAdapeter(context, noticias)
        view.recyclerViewOueAssistir.layoutManager = layoutManager
        view.recyclerViewOueAssistir.adapter = adapterOqueAssistir
        adapterOqueAssistir.setOnItemClickListener(object : OqueAssisirAdapeter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                context.trocarActivityComNoticia(ReproducaoVideoActivity(),
                    noticias[pos].titulo,noticias[pos].descricao,noticias[pos].conteudo,noticias[pos].foto,
                    noticias[pos].video,noticias[pos].data,noticias[pos].fonte,
                    noticias[pos].categoria,noticias[pos].duracao)
            }
        })
    }

}