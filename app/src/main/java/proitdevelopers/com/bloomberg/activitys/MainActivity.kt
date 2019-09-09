package proitdevelopers.com.bloomberg.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.viewPager
import kotlinx.android.synthetic.main.fragment_home.worm_dots_indicator
import proitdevelopers.com.bloomberg.MediaFragment
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.*
import proitdevelopers.com.bloomberg.fragmentos.BolsaDoisFragment
import proitdevelopers.com.bloomberg.fragmentos.BolsaFragment
import proitdevelopers.com.bloomberg.fragmentos.BolsaTresFragment
import proitdevelopers.com.bloomberg.fragmentos.HomeFragment

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.menu_home -> {
                substituirLayout(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_mercado -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_whatchlist ->{
                return@OnNavigationItemSelectedListener  true
            }
            R.id.menu_media ->{
                substituirLayout(MediaFragment())
                return@OnNavigationItemSelectedListener  true
            }
            R.id.menu_opcoes ->{
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        substituirLayout(HomeFragment())
    }

    private fun substituirLayout(fragment: Fragment){
        val transacaoFragmento = supportFragmentManager.beginTransaction()
        transacaoFragmento.replace(R.id.conteiner_root,fragment)
        transacaoFragmento.commit()
    }
}
