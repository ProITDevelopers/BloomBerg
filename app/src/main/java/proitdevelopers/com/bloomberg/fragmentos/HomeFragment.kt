package proitdevelopers.com.bloomberg.fragmentos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*

import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.activitys.PesquisarNoticiaActivity
import proitdevelopers.com.bloomberg.adapter.*
import proitdevelopers.com.bloomberg.modelo.Noticia
import proitdevelopers.com.bloomberg.modelo.valoresNoticias

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view pager das bolsas
        configurarViewPagerBolsas()
        configurarTopNews(valoresNoticias.noticias,view.context)
        configurarTendencias(valoresNoticias.noticias,view.context)
        configurarCategConteudo(valoresNoticias.noticias,view.context)
        configurarCategOQueAssistir(valoresNoticias.noticias,view.context)
        configurarCategTecnologia(valoresNoticias.noticias,view.context)
        configurarCategAoVivo(valoresNoticias.noticias,view.context)
        configurarCategCrypto(valoresNoticias.noticias,view.context)
        configurarRendaFixa(valoresNoticias.noticias,view.context)
        btnProcurarNoticia.setOnClickListener { pesquisarNoticia() }
    }

    private fun configurarViewPagerBolsas() {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(BolsaFragment())
        adapter.addFragment(BolsaDoisFragment())
        adapter.addFragment(BolsaTresFragment())
        viewPager.adapter = adapter
        worm_dots_indicator.setViewPager(viewPager)
    }


    private fun configurarTopNews(noticias: List<Noticia>,context: Context) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterTopNews = TopNewsAdapeter(context, noticias)
        recyclerTopNews.layoutManager = layoutManager
        recyclerTopNews.adapter = adapterTopNews
    }

    private fun configurarTendencias(noticias: List<Noticia>,context: Context) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfTendencias = TendenciasAdapeter(context, noticias)
        recyclerTendencias.layoutManager = layoutManager
        recyclerTendencias.adapter = adapterConfTendencias
    }

    private fun configurarCategConteudo(noticias: List<Noticia>,context: Context) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfConteudo = CategoriasOutrasAdapeter(context, noticias,3)
        recyclerViewCatConteudos.layoutManager = layoutManager
        recyclerViewCatConteudos.adapter = adapterConfConteudo
    }

    private fun configurarCategOQueAssistir(noticias: List<Noticia>,context: Context) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfTendencias = OqueAssisirAdapeter(context, noticias)
        recyclerViewOueAssistir.layoutManager = layoutManager
        recyclerViewOueAssistir.adapter = adapterConfTendencias
    }

    private fun configurarCategTecnologia(noticias: List<Noticia>,context: Context) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfTecnologia = CategoriasOutrasAdapeter(context, noticias,1)
        recyclerViewTecnologia.layoutManager = layoutManager
        recyclerViewTecnologia.adapter = adapterConfTecnologia
    }

    private fun configurarCategAoVivo(noticias: List<Noticia>,context: Context) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfAoVivo = AoVivoAdapeter(context, noticias)
        recyclerViewAoVivo.layoutManager = layoutManager
        recyclerViewAoVivo.adapter = adapterConfAoVivo
    }

    private fun configurarCategCrypto(noticias: List<Noticia>,context: Context) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfCrypto = CategoriasOutrasAdapeter(context, noticias,4)
        recyclerViewCrypto.layoutManager = layoutManager
        recyclerViewCrypto.adapter = adapterConfCrypto
    }

    private fun configurarRendaFixa(noticias: List<Noticia>,context: Context) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfRendaFixa = RendaFixaAdapeter(context, noticias)
        recyclerViewRendaFixa.layoutManager = layoutManager
        recyclerViewRendaFixa.adapter = adapterConfRendaFixa
    }

    fun pesquisarNoticia(){
        val intentPesquisaNoticia = Intent(context, PesquisarNoticiaActivity::class.java)
        startActivity(intentPesquisaNoticia)
    }
}