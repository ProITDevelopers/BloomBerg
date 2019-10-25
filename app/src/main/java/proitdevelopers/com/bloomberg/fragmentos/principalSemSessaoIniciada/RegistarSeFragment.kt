package proitdevelopers.com.bloomberg.fragmentos.principalSemSessaoIniciada


import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_registar.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.communs.*
import proitdevelopers.com.bloomberg.modelo.Usuario
import proitdevelopers.com.bloomberg.webService.ApInterface
import proitdevelopers.com.bloomberg.webService.ApiClientMediaRumo
import proitdevelopers.com.bloomberg.webService.erroApi.ErrorUtils
import retrofit2.Call
import retrofit2.Response

class RegistarSeFragment : Fragment() {

    var nome = ""
    var email = ""
    var senha = ""
    var senhaConfirmacao = ""
    var progressDialog: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registar, container, false)
        onClick(view)
        progressDialog(view.context)

        return view
    }


    private fun onClick(view: View) {
        //btn voltar
        view.frag_reg_btn_voltar.setOnClickListener {
            findNavController().navigate(RegistarSeFragmentDirections.actionRegistarSeFragmentToIniciarSessaoFragment())
        }

        view.frag_reg_btnEntrar.setOnClickListener {
            if (validarCampos(view)) {
                esconderTeclado(view.context as Activity)
                val usuario = Usuario()
                usuario.nome = nome
                usuario.email = email
                usuario.password = senha
                progressDialog!!.setMessage(msgQuasePronto)
                progressDialog!!.show()

                val service = ApiClientMediaRumo.buildSercice(ApInterface::class.java)
                val call = service.registrarCliente(usuario)

                call.enqueue(object : retrofit2.Callback<Void> {

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            view.context.mostrarMensagem(msgRegistroEfetuado)
                            progressDialog!!.dismiss()
                            findNavController().navigate(RegistarSeFragmentDirections.actionRegistarSeFragmentToIniciarSessaoFragment())
                        } else {
                            progressDialog!!.dismiss()
                            val errorResponce = ErrorUtils.parseError(response)
                            errorResponce!!.error?.let { it1 ->
                                view.context.erroEditText(view.frag_resgistar_email_edit,it1)
                            }
                            Log.i("ERRO_API", response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        progressDialog!!.dismiss()
                        Log.i("falja", t.toString())
                        view.context.verificacaoOnFailedApi(t)
                    }
                })
            }
        }

    }

    private fun progressDialog(context: Context) {
        progressDialog = ProgressDialog(context, R.style.MyAlertDialogStyle)
        progressDialog!!.setCancelable(false)
    }

    private fun validarCampos(view: View): Boolean {

        view.frag_resgistar_nome_edit?.let {
            nome = view.frag_resgistar_nome_edit.text.toString().trim()
        }

        view.frag_resgistar_email_edit?.let {
            email = view.frag_resgistar_email_edit.text.toString().trim().toLowerCase()
        }

        view.frag_resgistar_senha_edit?.let {
            senha = view.frag_resgistar_senha_edit.text.toString()
        }

        view.frag_resgistar_senha_conf_edit?.let {
            senhaConfirmacao = view.frag_resgistar_senha_conf_edit.text.toString()
        }

        if (view.context.campoVazio(nome, view.frag_resgistar_nome_edit)) {
            return false
        }

        if (!view.context.validarEmail(email)) {
            view.context.erroEditText(view.frag_resgistar_email_edit, MSG_ERRO_EMAIL)
            return false
        }

        if (view.context.campoVazio(email, view.frag_resgistar_email_edit)) {
            return false
        }

        if (view.context.senhaFraca(senha)) {
            view.context.erroEditText(view.frag_resgistar_senha_edit, MSG_ERRO_SENHA_FRACA)
            return false
        }

        if (view.context.campoVazio(senha, view.frag_resgistar_senha_edit)) {
            return false
        }

        if (view.context.campoVazio(senhaConfirmacao, view.frag_resgistar_senha_conf_edit)) {
            return false
        }

        if (view.context.camposDiferentes(senha, senhaConfirmacao, view.frag_resgistar_senha_conf_edit)) {
            return false
        }
        return true
    }

}
