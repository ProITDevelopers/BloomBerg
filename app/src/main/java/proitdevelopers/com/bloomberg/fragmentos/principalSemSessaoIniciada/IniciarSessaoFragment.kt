package proitdevelopers.com.bloomberg.fragmentos.principalSemSessaoIniciada

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_iniciar_sessao.view.*
import proitdevelopers.com.bloomberg.R
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient


class IniciarSessaoFragment : Fragment(), GoogleApiClient.OnConnectionFailedListener {
    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(context,""+p0,Toast.LENGTH_SHORT).show()
    }


    //Google
    companion object{
        private val RC_SIGIN_IN = 9991
    }

    lateinit var mGoogleApiClient: GoogleApiClient

    //Facebook
    private var callbackManager: CallbackManager = CallbackManager.Factory.create()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_iniciar_sessao, container, false)

        FacebookSdk.sdkInitialize(view.context)
        AppEventsLogger.activateApp(view.context,view.context.resources.getString(R.string.facebook_app_id))

        view.frag_reg_btnEntrar.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_iniciarSessaoFragment_to_entrarFragment)
        )

        view.btnCriarConta.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_iniciarSessaoFragment_to_registarSeFragment)
        )

        //configureGoogleClient(view)

        facebookInit(view)

        return  view
    }

    private fun configureGoogleClient(context: View){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("850306275089-qak91c7avv447sl8abq74p1s60vqvu4h.apps.googleusercontent.com")
            .requestEmail()
            .build()

        mGoogleApiClient = GoogleApiClient.Builder(context.context)
                .enableAutoManage(getContext() as FragmentActivity,this)
            .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
            .build()
        mGoogleApiClient.connect()

        context.btnGoogleEntrar.setOnClickListener {
            entrarGoogle()
        }

    }

    private fun entrarGoogle(){
        val intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(intent, RC_SIGIN_IN)
    }

    private fun facebookInit(view: View) {

        //view.loginBtnFacebook

        view.loginBtnFacebook.setOnClickListener{
            LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("public_profile","email"))
            LoginManager.getInstance().registerCallback(callbackManager,object:FacebookCallback<LoginResult>{
                override fun onSuccess(loginResult: LoginResult) {
                    Log.d("InicioSessao"," FAcebook token: " + loginResult.accessToken.token)
                }

                override fun onCancel() {
                    Log.d("InicioSessao"," FACEBOOK Cancel")
                }

                override fun onError(exception: FacebookException) {
                    Log.d("InicioSessao",exception.message.plus(" FACEBOOK OnError"))
                }
            })
        }

        view.btnSairFacebook.setOnClickListener{
            GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/permissions/",
                null,
                HttpMethod.DELETE,
                GraphRequest.Callback {
                    LoginManager.getInstance().logOut()
                }).executeAsync()
        }

        view.loginBtnFacebook.setReadPermissions(Arrays.asList("public_profile","email"))
        view.loginBtnFacebook.setFragment(this)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("requestcoood",""+requestCode)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode ==64206)
            callbackManager.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGIN_IN ){
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)
            if(result.isSuccess){
                val account = result.signInAccount
                val idToken =  account?.idToken
                Log.d("idToken",idToken)
            }else{
                Log.d("idToken","falha")
                Log.d("idToken", "sndsd"+result.status)
                Log.d("idToken", "sndsd"+result)
            }
        }
    }

    fun facebookApp(){
        //FacebookSdk.sdkInitialize(context)
       //AppEventsLogger.activateApp(context)

        try {
            val pm = activity!!.packageManager
            val info = pm.getPackageInfo(
                "proitdevelopers.com.bloomberg",
                PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }

    }

}
