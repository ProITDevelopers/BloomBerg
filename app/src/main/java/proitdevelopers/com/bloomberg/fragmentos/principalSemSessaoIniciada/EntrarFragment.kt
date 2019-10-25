package proitdevelopers.com.bloomberg.fragmentos.principalSemSessaoIniciada

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_entrar.view.*
import kotlinx.android.synthetic.main.fragment_entrar.view.btnIniciarSessao
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.communs.*
import proitdevelopers.com.bloomberg.modelo.Data
import proitdevelopers.com.bloomberg.webService.ApInterface
import proitdevelopers.com.bloomberg.webService.ApiClientMediaRumo
import proitdevelopers.com.bloomberg.webService.erroApi.ErrorUtils
import retrofit2.Call
import retrofit2.Response
import android.R.attr.password
import android.text.InputType
import android.widget.CompoundButton
import android.R.id.checkbox
import android.R.attr.password
import android.R.id.checkbox
import android.widget.RadioGroup


class EntrarFragment : Fragment() {

    var email = ""
    var senha = ""
    var progressDialog: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_entrar, container, false)

        progressDialog(view.context)

        onClick(view)

        return view
    }

    private fun onClick(view: View){


        view.btnIniciarSessao.setOnClickListener{
            if (validarCampos(view)) {
                esconderTeclado(view.context as Activity)
                progressDialog!!.setMessage(msgQVerificando)
                progressDialog!!.show()

                val service = ApiClientMediaRumo.buildSercice(ApInterface::class.java)
                val call = service.autenticarCliente(email,senha)

                call.enqueue(object : retrofit2.Callback<Data> {

                    override fun onResponse(call: Call<Data>, response: Response<Data>) {
                        if (response.isSuccessful) {
                            progressDialog!!.dismiss()
                            findNavController().navigate(EntrarFragmentDirections.actionEntrarFragmentToHomeFragmentSemSessaoTosessao())
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
        }

        view.frg_entar_fechar.setOnClickListener {
            findNavController().navigate(EntrarFragmentDirections.actionEntrarFragmentToIniciarSessaoFragment2())
        }

        view.checkBoxMostrarSenha.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, mostar: Boolean) {
                if (mostar)
                    view.frag_entrar_edtSenhaUser.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                else
                    view.frag_entrar_edtSenhaUser.inputType = 129
            }

        })
    }

    private fun progressDialog(context: Context) {
        progressDialog = ProgressDialog(context, R.style.MyAlertDialogStyle)
        progressDialog!!.setCancelable(false)
    }


    private fun  validarCampos(view:View): Boolean {

        view.frag_entrar_edtEmailUser?.let {
            view.context.limparErroEditTxt(view.frag_entrar_edtEmailUser)
            email = view.frag_entrar_edtEmailUser.text.toString().trim()
        }

        view.frag_entrar_edtSenhaUser?.let {
            view.context.limparErroEditTxt(view.frag_entrar_edtSenhaUser)
            senha = view.frag_entrar_edtSenhaUser.text.toString()
        }

        if(!view.context.validarEmail(email) or TextUtils.isEmpty(email)){
            view.context.erroEditText(view.frag_entrar_edtEmailUser,MSG_ERRO_EMAIL)
            return false
        }

        if (TextUtils.isEmpty(senha)){
            view.context.erroEditText(view.frag_entrar_edtSenhaUser,MSG_ERRO_VAZIO_CAMPO)
            return false
        }

        return true
    }

}