package proitdevelopers.com.bloomberg.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.recyclerTopNews
import kotlinx.android.synthetic.main.fragment_home.viewPager
import kotlinx.android.synthetic.main.fragment_home.worm_dots_indicator
import kotlinx.android.synthetic.main.fragment_home.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.CategoriasOutrasAdapeter
import proitdevelopers.com.bloomberg.adapter.TendenciasAdapeter
import proitdevelopers.com.bloomberg.adapter.TopNewsAdapeter
import proitdevelopers.com.bloomberg.adapter.ViewPagerAdapter
import proitdevelopers.com.bloomberg.fragment.BolsaDoisFragment
import proitdevelopers.com.bloomberg.fragment.BolsaFragment
import proitdevelopers.com.bloomberg.fragment.BolsaTresFragment
import proitdevelopers.com.bloomberg.modelo.Noticia
import proitdevelopers.com.bloomberg.modelo.valoresNoticias

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

       //view pager das bolsas
       configurarViewPagerBolsas()
       configurarTopNews(valoresNoticias.noticias)
       configurarTendencias(valoresNoticias.noticias)
        configurarCategConteudo(valoresNoticias.noticias)



    }

    private fun configurarViewPagerBolsas() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(BolsaFragment())
        adapter.addFragment(BolsaDoisFragment())
        adapter.addFragment(BolsaTresFragment())
        viewPager.adapter = adapter
        worm_dots_indicator.setViewPager(viewPager)
    }

    private fun configurarTopNews(noticias: List<Noticia>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterTopNews = TopNewsAdapeter(this@MainActivity, noticias)
        recyclerTopNews.layoutManager = layoutManager
        recyclerTopNews.adapter = adapterTopNews
    }

    private fun configurarTendencias(noticias: List<Noticia>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfTendencias = TendenciasAdapeter(this@MainActivity, noticias)
        recyclerTendencias.layoutManager = layoutManager
        recyclerTendencias.adapter = adapterConfTendencias
    }

    private fun configurarCategConteudo(noticias: List<Noticia>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfTendencias = CategoriasOutrasAdapeter(this@MainActivity, noticias)
        recyclerViewCatConteudos.layoutManager = layoutManager
        recyclerViewCatConteudos.adapter = adapterConfTendencias
    }
}
