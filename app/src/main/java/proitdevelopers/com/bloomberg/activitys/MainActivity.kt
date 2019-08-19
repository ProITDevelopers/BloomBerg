package proitdevelopers.com.bloomberg.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.widget.Toast
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.fragment.BolsaDoisFragment
import proitdevelopers.com.bloomberg.fragment.BolsaFragment
import proitdevelopers.com.bloomberg.fragment.BolsaTresFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(BolsaFragment())
        adapter.addFragment(BolsaDoisFragment())
        adapter.addFragment(BolsaTresFragment())
        viewPager.adapter = adapter
        worm_dots_indicator.setViewPager(viewPager)

    }

    class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager){

        private val fragmentList : MutableList<Fragment> = ArrayList()

        override fun getItem(p0: Int): Fragment {
            return fragmentList[p0]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment){
            fragmentList.add(fragment)
        }

    }
}
