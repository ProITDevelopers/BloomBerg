package proitdevelopers.com.bloomberg.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada.HomeFragment

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            /*R.id.menu_home -> {
                substituirLayout(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }*/
            R.id.menu_mercado -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_whatchlist ->{
                return@OnNavigationItemSelectedListener  true
            }
            /*R.id.menu_media ->{
            substituirLayout(MediaFragment())
            return@OnNavigationItemSelectedListener  true
            }*/
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

    fun substituirLayout(fragment: Fragment){
        val transacaoFragmento = supportFragmentManager.beginTransaction()
        transacaoFragmento.replace(R.id.conteiner_root,fragment)
        transacaoFragmento.commit()
    }
}
