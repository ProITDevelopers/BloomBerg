package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import proitdevelopers.com.bloomberg.R

class BolsaTresFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bolsa_tres, container, false)
    }
}
