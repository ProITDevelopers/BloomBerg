package proitdevelopers.com.bloomberg.fragmentos


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_bolsa.*

import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.BolsaAdapeter
import proitdevelopers.com.bloomberg.modelo.ValoresBolsa

class BolsaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bolsa, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val adapter = activity?.let { BolsaAdapeter(it, ValoresBolsa.bolsas) }
        ViewCompat.setNestedScrollingEnabled(recyclerViewBolsas, false)
        recyclerViewBolsas.layoutManager = layoutManager
        recyclerViewBolsas.adapter = adapter
    }

}
