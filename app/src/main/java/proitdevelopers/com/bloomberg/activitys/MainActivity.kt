package proitdevelopers.com.bloomberg.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
