package proitdevelopers.com.bloomberg.activitys

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_pesquisar_noticia.*
import kotlinx.android.synthetic.main.fragment_home.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.TopNewsAdapeter
import proitdevelopers.com.bloomberg.adapter.TopicosNoticiaAdapeter
import proitdevelopers.com.bloomberg.modelo.Noticia
import proitdevelopers.com.bloomberg.modelo.Topico
import proitdevelopers.com.bloomberg.modelo.valorTopico.topicos

class PesquisarNoticiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisar_noticia)

        configurarTopNews(topicos)
    }

    private fun configurarTopNews(topicos: List<Topico>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val adapterTopNews = TopicosNoticiaAdapeter(this@PesquisarNoticiaActivity, topicos)
        recyclerViewTopicos.layoutManager = layoutManager
        recyclerViewTopicos.adapter = adapterTopNews
    }

    fun cancelarPesquisa(view: View){
        val intentHome = Intent(view.context,MainActivity::class.java)
        startActivity(intentHome)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}

/*Pesuisar
editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            performSearch();
            return true;
        }
        return false;
    }
});*/
