package proitdevelopers.com.bloomberg.fragmentos.principalSemSessaoIniciada
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import proitdevelopers.com.bloomberg.R

class HomeFragment_sem_sessaoTosessao : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_fragment_sem_sessao_tosessao, container, false)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentConteinerSessao_) as NavHostFragment

        view.findViewById<BottomNavigationView>(R.id.bottom_navigation_)
            .setupWithNavController(navController = navHostFragment.navController)
        return view
    }

}
