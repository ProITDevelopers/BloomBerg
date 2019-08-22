package proitdevelopers.com.bloomberg.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_bolsa.*
import kotlinx.android.synthetic.main.fragment_bolsa.view.*

import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.BolsaAdapeter
import proitdevelopers.com.bloomberg.modelo.Bolsa
import proitdevelopers.com.bloomberg.modelo.ValoresBolsa

class BolsaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_bolsa, container, false)
        return viewRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val adapter = activity?.let { BolsaAdapeter(it, ValoresBolsa.bolsas) }
        recyclerViewBolsas.layoutManager = layoutManager
        recyclerViewBolsas.adapter = adapter
    }

}
