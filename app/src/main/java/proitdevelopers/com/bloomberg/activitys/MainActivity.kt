package proitdevelopers.com.bloomberg.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.recyclerTopNews
import kotlinx.android.synthetic.main.fragment_home.viewPager
import kotlinx.android.synthetic.main.fragment_home.worm_dots_indicator
import kotlinx.android.synthetic.main.fragment_home.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.*
import proitdevelopers.com.bloomberg.fragmentos.BolsaDoisFragment
import proitdevelopers.com.bloomberg.fragmentos.BolsaFragment
import proitdevelopers.com.bloomberg.fragmentos.BolsaTresFragment
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
       configurarCategOQueAssistir(valoresNoticias.noticias)
       configurarCategTecnologia(valoresNoticias.noticias)
       configurarCategAoVivo(valoresNoticias.noticias)
       configurarCategCrypto(valoresNoticias.noticias)
       configurarRendaFixa(valoresNoticias.noticias)

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
        val adapterConfConteudo = CategoriasOutrasAdapeter(this@MainActivity, noticias,3)
        recyclerViewCatConteudos.layoutManager = layoutManager
        recyclerViewCatConteudos.adapter = adapterConfConteudo
    }

    private fun configurarCategOQueAssistir(noticias: List<Noticia>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfTendencias = OqueAssisirAdapeter(this@MainActivity, noticias)
        recyclerViewOueAssistir.layoutManager = layoutManager
        recyclerViewOueAssistir.adapter = adapterConfTendencias
    }

    private fun configurarCategTecnologia(noticias: List<Noticia>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfTecnologia = CategoriasOutrasAdapeter(this@MainActivity, noticias,1)
        recyclerViewTecnologia.layoutManager = layoutManager
        recyclerViewTecnologia.adapter = adapterConfTecnologia
    }

    private fun configurarCategAoVivo(noticias: List<Noticia>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfAoVivo = AoVivoAdapeter(this@MainActivity, noticias)
        recyclerViewAoVivo.layoutManager = layoutManager
        recyclerViewAoVivo.adapter = adapterConfAoVivo
    }

    private fun configurarCategCrypto(noticias: List<Noticia>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfCrypto = CategoriasOutrasAdapeter(this@MainActivity, noticias,4)
        recyclerViewCrypto.layoutManager = layoutManager
        recyclerViewCrypto.adapter = adapterConfCrypto
    }

    private fun configurarRendaFixa(noticias: List<Noticia>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterConfRendaFixa = RendaFixaAdapeter(this@MainActivity, noticias)
        recyclerViewRendaFixa.layoutManager = layoutManager
        recyclerViewRendaFixa.adapter = adapterConfRendaFixa
    }
}
