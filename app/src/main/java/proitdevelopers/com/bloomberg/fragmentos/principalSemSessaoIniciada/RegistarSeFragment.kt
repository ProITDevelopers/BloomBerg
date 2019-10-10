package proitdevelopers.com.bloomberg.fragmentos.principalSemSessaoIniciada


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_registar.view.*
import proitdevelopers.com.bloomberg.R

class RegistarSeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registar, container, false)

        onClick(view)

        return view
    }

    private fun onClick(view: View){
        //btn voltar
        view.frag_reg_btn_voltar.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_registarSeFragment_to_iniciarSessaoFragment)
        )

        view.frag_reg_btnEntrar.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_registarSeFragment_to_homeFragment_sem_sessaoTosessao)
        )
    }

}
