package proitdevelopers.com.bloomberg


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

class SplashFragment : Fragment() {

    val TEMPO_SPLASH = 3000

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        lancarJanelaHomeSessao()
        return view
    }

    private fun lancarJanelaHomeSessao(){
        Handler().postDelayed(object :Runnable{
            override fun run() {
                findNavController().navigate(R.id.action_splashFragment_to_iniciarSessaoFragment)
            }
        },TEMPO_SPLASH.toLong())
    }

}
