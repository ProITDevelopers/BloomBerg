package proitdevelopers.com.bloomberg.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.CallbackManager
import proitdevelopers.com.bloomberg.R
import com.facebook.appevents.AppEventsLogger
import com.facebook.FacebookSdk
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import java.util.Arrays.asList
import android.R.id
import com.facebook.login.widget.LoginButton

class SemSessaoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sem_sessao)
    }
}
