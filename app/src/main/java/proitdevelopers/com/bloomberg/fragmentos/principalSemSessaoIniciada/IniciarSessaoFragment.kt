package proitdevelopers.com.bloomberg.fragmentos.principalSemSessaoIniciada

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
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
import java.util.*
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_sem_sessao.*
import proitdevelopers.com.bloomberg.communs.*
import proitdevelopers.com.bloomberg.modelo.Data
import proitdevelopers.com.bloomberg.webService.ApInterface
import proitdevelopers.com.bloomberg.webService.ApiClientMediaRumoFaceBook
import proitdevelopers.com.bloomberg.webService.erroApi.ErrorUtils
import retrofit2.Call
import retrofit2.Response


class IniciarSessaoFragment : Fragment(), GoogleApiClient.OnConnectionFailedListener {
    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(context,""+p0,Toast.LENGTH_SHORT).show()
    }


    //Google
    companion object{
        private val RC_SIGIN_IN = 9991
    }

    lateinit var mGoogleApiClient: GoogleApiClient
    private var checkCurrentDestination = false
    //Facebook_
    private var callbackManager: CallbackManager = CallbackManager.Factory.create()

    var progressDialog: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_iniciar_sessao, container, false)

        FacebookSdk.sdkInitialize(view.context)
        AppEventsLogger.activateApp(view.context,view.context.resources.getString(R.string.facebook_app_id))
        progressDialog(view.context)
        view.frag_reg_btnEntrar.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_iniciarSessaoFragment_to_entrarFragment)
        )

        view.btnCriarConta.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_iniciarSessaoFragment_to_registarSeFragment)
        )
        view.loginBtnFacebook.setReadPermissions(Arrays.asList("public_profile","email"))
        view.loginBtnFacebook.setFragment(this)
        //configureGoogleClient(view)

        facebookInit(view)

        return  view
    }

    private fun progressDialog(context: Context) {
        progressDialog = ProgressDialog(context, R.style.MyAlertDialogStyle)
        progressDialog!!.setCancelable(false)
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
            callbackManager = CallbackManager.Factory.create()
            //LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("public_profile","email"))
            LoginManager.getInstance()
                .registerCallback(callbackManager,object:FacebookCallback<LoginResult>{
                    override fun onSuccess(loginResult: LoginResult) {
                        if (loginResult.accessToken!=null){
                            enviarTokenOAuthAutenticacao(loginResult.accessToken.token,view)
                        }
                    }

                    override fun onCancel() {
                        view.context.mostrarMensagem(msgAlgumCancelamento)
                    }

                    override fun onError(exception: FacebookException) {
                        view.context.verificacaoOnFailedApi()
                    }
                })
            //LoginManager.getInstance()
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
    }

    fun enviarTokenOAuthAutenticacao(access_token:String,view:View){

        progressDialog!!.setMessage(msgQVerificando)
        progressDialog!!.show()

        val service = ApiClientMediaRumoFaceBook.buildSercice(ApInterface::class.java)
        val call = service.autenticarFacebook(access_token)
        call.enqueue(object : retrofit2.Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.i("ERRO_API", " fgf\n"+response.body())
                    progressDialog!!.dismiss()
                    findNavController()
                        .navigate(IniciarSessaoFragmentDirections
                            .actionIniciarSessaoFragmentToHomeFragmentSemSessaoTosessao())
                } else {
                    progressDialog!!.dismiss()
                    val errorResponce = ErrorUtils.parseError(response)
                    errorResponce!!.error?.let { it1 ->
                        view.context.mostrarMensagem(it1)
                    }
                    Log.i("ERRO_API", response.code().toString())
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                progressDialog!!.dismiss()
                Log.i("falja", t.toString())
                view.context.verificacaoOnFailedApi(t)
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("requestcoood",""+requestCode)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode ==64206){
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
        /*if (requestCode == RC_SIGIN_IN ){
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
        }*/
    }

    /*fun facebookApp(){
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

    }*/

}
