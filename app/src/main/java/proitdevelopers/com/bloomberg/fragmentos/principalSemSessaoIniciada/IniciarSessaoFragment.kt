package proitdevelopers.com.bloomberg.fragmentos.principalSemSessaoIniciada


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_iniciar_sessao.view.*
import proitdevelopers.com.bloomberg.R


class IniciarSessaoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_iniciar_sessao, container, false)

        view.btnEntrar.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_iniciarSessaoFragment_to_entrarFragment)
        )

        view.btnCriarConta.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_iniciarSessaoFragment_to_registarSeFragment)
        )

        return  view
    }


}
