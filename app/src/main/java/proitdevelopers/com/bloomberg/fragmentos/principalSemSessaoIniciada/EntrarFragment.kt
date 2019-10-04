package proitdevelopers.com.bloomberg.fragmentos.principalSemSessaoIniciada

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_entrar.view.*
import proitdevelopers.com.bloomberg.R

class EntrarFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_entrar, container, false)
        view.btnIniciarSessao.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_entrarFragment_to_homeFragment_sem_sessaoTosessao)
        )
        return view
    }
}