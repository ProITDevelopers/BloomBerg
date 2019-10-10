package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_nossos_meios.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.fragmentos.SiteMercadoFragment
import proitdevelopers.com.bloomberg.fragmentos.SiteRumoFragment
import proitdevelopers.com.bloomberg.fragmentos.SiteVanguardaFragment
import proitdevelopers.com.bloomberg.adapter.nossosMeios.NossosMeiosAdapter

class NossosMeiosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_nossos_meios, container, false)
        viewPagesSites(view)
        return view
    }


    private fun viewPagesSites(view: View) {

        val adapter = NossosMeiosAdapter(childFragmentManager)
        adapter.addFragment(SiteRumoFragment(),"Rumo")
        adapter.addFragment(SiteVanguardaFragment(),"Vanguarda")
        adapter.addFragment(SiteMercadoFragment(),"Mercado")
        view.view_pager_sites.adapter = adapter
        view.tabs.setupWithViewPager(view.view_pager_sites)

    }


}
