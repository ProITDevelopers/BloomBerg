package proitdevelopers.com.bloomberg


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_noticias_salvas.view.*
import proitdevelopers.com.bloomberg.adapter.noticias_guardadas.NoticiasGuardadasAdapeter
import proitdevelopers.com.bloomberg.basededados.entitys.Noticia
import proitdevelopers.com.bloomberg.communs.noticiaViewModel
import proitdevelopers.com.bloomberg.viewModel.NoticiaViewModel

class NoticiasSalvasFragment : Fragment(),NoticiasGuardadasAdapeter.OnDeleteClickListner {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_noticias_salvas, container, false)

        noticiaViewModel = ViewModelProviders.of(this).get(NoticiaViewModel::class.java)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfNoticiasGuardadas = NoticiasGuardadasAdapeter(view.context,this)
        view.recyclerViewNoticiasFavorito.layoutManager = layoutManager
        view.recyclerViewNoticiasFavorito.adapter = adapterConfNoticiasGuardadas

        noticiaViewModel.todasNoticiasFavoritas.observe(this, Observer {noticias ->
            noticias?.let {
                adapterConfNoticiasGuardadas.setNoticias(noticias)
            }
        })

        return view
    }

    override fun onDeleteClickListner(noticiaFavorita: Noticia) {
        noticiaViewModel.deleteNoticiaFavorita(noticiaFavorita)
    }


}
