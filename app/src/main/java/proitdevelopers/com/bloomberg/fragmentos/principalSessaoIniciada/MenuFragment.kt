package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_menu.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.menu_sessoes.MenuSessoesAdapeter
import proitdevelopers.com.bloomberg.modelo.Menu
import proitdevelopers.com.bloomberg.modelo.valoresMenu
import proitdevelopers.com.bloomberg.viewModel.NoticiaViewModel

class MenuFragment : Fragment() {

    private lateinit var noticiaViewModel: NoticiaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        noticiaViewModel = ViewModelProviders.of(this).get(NoticiaViewModel::class.java)

        noticiaViewModel.todasNoticiasFavoritas.observe(this, Observer {noticias ->
            noticias?.let {
                view.frag_menu_qtd_noticias.text = noticias.size.toString()
            }
        })

        configurarSessoesMenu(valoresMenu.valoresMenu,view.context,view,activity)
        onClick(view)

        return view
    }

    private fun configurarSessoesMenu(
        sessoes: List<Menu>,
        context: Context,
        view: View,
        activity: FragmentActivity?
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfDestaque = MenuSessoesAdapeter(context, sessoes,activity)
        view.recyclerViewMenu_principal.layoutManager = layoutManager
        view.recyclerViewMenu_principal.adapter = adapterConfDestaque
    }

    private fun onClick(view: View){
        view.fra_menu_sair_menu.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_homeFragment)
        )

        view.fra_menu_pesquisar_menu.setOnClickListener (
           Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_pesquisaNoticiaFragment)
        )

        view.frag_menu_noticias_salvas.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_noticiasSalvasFragment)
        )
    }
}
