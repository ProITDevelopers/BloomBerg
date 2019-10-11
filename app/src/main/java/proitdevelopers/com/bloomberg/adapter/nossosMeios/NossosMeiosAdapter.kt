package proitdevelopers.com.bloomberg.adapter.nossosMeios

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class NossosMeiosAdapter(manager:FragmentManager):FragmentPagerAdapter(manager) {

    private val listaFragmentos: MutableList<Fragment> = ArrayList()
    private val titleList: MutableList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return listaFragmentos[position]
    }

    override fun getCount(): Int = listaFragmentos.size

    fun addFragment(fragment: Fragment,title:String){
        listaFragmentos.add(fragment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }


}