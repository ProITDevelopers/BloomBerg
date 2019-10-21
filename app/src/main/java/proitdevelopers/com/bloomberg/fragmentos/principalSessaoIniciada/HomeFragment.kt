package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.activitys.ReproducaoVideoActivity
import proitdevelopers.com.bloomberg.adapter.*
import proitdevelopers.com.bloomberg.basededados.entitys.Noticia
import proitdevelopers.com.bloomberg.communs.*
import proitdevelopers.com.bloomberg.modelo.valoresNoticias
import proitdevelopers.com.bloomberg.viewModel.NoticiaViewModel
import java.util.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        noticiaViewModel = ViewModelProviders.of(this).get(NoticiaViewModel::class.java)

        //configurarViewPagerBolsas(view)
        configurarDestaqueInicial(view,valoresNoticias.noticias.get(0))
        configurarDestaques(valoresNoticias.noticias,view.context,view)
        configurarMercado(valoresNoticias.noticias,view.context,view)
        configurarCategInternacional(valoresNoticias.noticias,view.context,view)
        configurarCategPolitica(valoresNoticias.noticias,view.context,view)
        configurarCategOpniao(valoresNoticias.noticias,view.context,view)
        configurarCatCultura(valoresNoticias.noticias,view.context,view)
        configurarCatBusiness(valoresNoticias.noticias,view.context,view)
        configurarCatBolsa(valoresNoticias.noticias,view.context,view)
        configurarCategOQueAssistir(valoresNoticias.noticias,view.context,view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TAG = "HomeFragmentDebug"
        img_destaque.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detalheNoticiaFragment)
        )

        frag_home_btn_proc_noticia.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_pesquisaNoticiaFragment)
        )

        //view pager das bolsas
        configurarViewPagerBolsas()
       //Travis Scott - HIGHEST IN THE ROOM
    }

    private fun configurarDestaqueInicial(view:View,noticia: Noticia){

        noticia.let {
            view.context.carregarFoto(noticia.foto,view.img_destaque)
            view.destaque_cat_tv.text = noticia.categoria
            view.destaque_noticia_titulo.text = noticia.titulo
            view.destaque_noticia_descricao.text = noticia.descricao
            view.destaque_noticia_data_tv.text = noticia.data
        }

        view.destaque_partilhar_ic.setOnClickListener {
            view.context.partilharNoticia(noticia.titulo,noticia.descricao,noticia.conteudo)
        }

        view.destaque_favoritos_tv.setOnClickListener {
            noticia.let {
                view.context.guardarNoticiaOffiline(noticia)
            }
        }
    }

    private fun configurarViewPagerBolsas() {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(BolsaFragment())
        adapter.addFragment(BolsaDoisFragment())
        //adapter.addFragment(BolsaTresFragment())
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
            noticias,0,gerarNumRand(0,valoresNoticias.noticias.size),noticiaViewModel)
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
        val adapterConfInternacional = CategoriasOutrasAdapeter(
            context,
            noticias, 1, gerarNumRand(0,noticias.size), noticiaViewModel
        )
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
        val adapterConfPolitica = CategoriasOutrasAdapeter(
            context,
            noticias, 2, gerarNumRand(0,
                noticias.size), noticiaViewModel
        )
        view.recyclerViewPolitica.layoutManager = layoutManager
        view.recyclerViewPolitica.adapter = adapterConfPolitica
    }

    private fun configurarCategOpniao(
        noticias: MutableList<Noticia>,
        context: Context,
        view: View
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        //val adapterConfAoVivo = AoVivoAdapeter(context, aProitdevelopers.com.bloomberg.basededados.entitys.noticias)
        val adapterConfSociedade = CategoriasOutrasAdapeter(
            context,
            noticias, 3, gerarNumRand(0,noticias.size), noticiaViewModel
        )
        view.recyclerViewOpniao.layoutManager = layoutManager
        view.recyclerViewOpniao.adapter = adapterConfSociedade
    }

    private fun configurarCatCultura(
        noticias: MutableList<Noticia>,
        context: Context,
        view: View
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfCrypto = CategoriasOutrasAdapeter(
            context,
            noticias, 4, gerarNumRand(0,noticias.size), noticiaViewModel
        )
        view.recyclerViewCultura.layoutManager = layoutManager
        view.recyclerViewCultura.adapter = adapterConfCrypto
    }

     private fun configurarCatBusiness(
         noticias: MutableList<Noticia>,
         context: Context,
         view: View
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfCrypto = CategoriasOutrasAdapeter(
            context,
            noticias, 5, gerarNumRand(0,noticias.size), noticiaViewModel
        )
        view.recyclerViewBusiness.layoutManager = layoutManager
        view.recyclerViewBusiness.adapter = adapterConfCrypto
    }


    private fun configurarCatBolsa(
        noticias: MutableList<Noticia>,
        context: Context,
        view: View
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfCrypto = CategoriasOutrasAdapeter(
            context,
            noticias, 6, gerarNumRand(0,noticias.size), noticiaViewModel
        )
        view.recyclerViewBolsa.layoutManager = layoutManager
        view.recyclerViewBolsa.adapter = adapterConfCrypto
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
                    noticias[pos].titulo,noticias[pos].conteudo,noticias[pos].foto,
                    noticias[pos].video,noticias[pos].data,noticias[pos].fonte,
                    noticias[pos].categoria,noticias[pos].duracao,noticias[pos].duracao)
            }
        })
    }

}