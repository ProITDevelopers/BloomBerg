package proitdevelopers.com.bloomberg.communs

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View.GONE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.basededados.entitys.Noticia
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.util.*

fun esconderSeparador(imgSeparador:ImageView,posicao:Int,limite:Int){
    if (posicao==limite-1)
        imgSeparador.visibility = GONE
}

fun Context.partilharNoticia(titulo:String,descricao:String,conteudo:String){
    val noticia = titulo.plus("\n\n").plus(descricao).plus("\n\n").plus(conteudo)
        .plus("\n\n")
        .plus("MEDIA RUMO")
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.putExtra(Intent.EXTRA_TEXT,noticia)
    intent.type = "text/plain"
    startActivity(Intent.createChooser(intent,"Partilhar para:"))
}

fun logDebug(TAG:String, mensagem:String){
    Log.i(TAG,mensagem)
}

fun Context.trocarActivityComNoticia(
        activity: Activity,
        TITULO_:String,DESCRICAO_:String,CONTEUDO_:String,
        FOTO_:String,VIDEO_:String,DATA_:String,
        FONTE_:String,CATEGORIA_:String,DURACAO_:String){

            val intent = Intent(applicationContext,activity::class.java)
            intent.putExtra(TITULO,TITULO_)
            intent.putExtra(DESCRICAO,DESCRICAO_)
            intent.putExtra(CONTEUDO,CONTEUDO_)
            intent.putExtra(FOTO,FOTO_)
            intent.putExtra(VIDEO,VIDEO_)
            intent.putExtra(DATA,DATA_)
            intent.putExtra(FONTE,FONTE_)
            intent.putExtra(CATEGORIA,CATEGORIA_)
            intent.putExtra(DURACAO,DURACAO_)
            startActivity(intent)
}

fun gerarNumRand(comecaEm:Int,terminaEm:Int):Int{
    val retorno = (comecaEm..terminaEm).random()
    if (retorno>5)
        return (comecaEm..3).random()
    else return retorno
}

fun Context.guardarNoticiaOffiline(noticia: Noticia){
    noticia.id = UUID.randomUUID().toString()
    noticiaViewModel.insertNoticiasFavoritas(noticia)
    Toast.makeText(this, "Noticia guardada nos favoritos", Toast.LENGTH_SHORT).show()
}

fun Context.carregarFoto(url:String,imageView: ImageView){
    Glide.with(this)
        .load(url)
        .apply(RequestOptions.placeholderOf(R.drawable.media_rumo_default).error(R.drawable.media_rumo_default))
        .into(imageView)
}

fun Context.validarEmail(email:String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

fun Context.limparErroEditTxt(editexto:EditText){
    editexto.error = null
}

fun Context.erroEditText(editexto: EditText,msg:String){
    editexto.requestFocus()
    editexto.error = msg
}

fun Context.mostrarMensagem(mensagem: String){
    Toast.makeText(this,mensagem,Toast.LENGTH_SHORT).show()
}

fun Context.senhaFraca(senha:String):Boolean{
    if (senha.length < 5)
        return true
    return false
}


fun Context.campoVazio(campoTxt:String,editText: EditText,mensagem:String = MSG_ERRO_VAZIO_CAMPO):Boolean{
    if (TextUtils.isEmpty(campoTxt)){
        erroEditText(editText,mensagem)
        return true
    }
    return false
}

fun Context.camposDiferentes(valor1:String,valor2:String,editText: EditText):Boolean{
    if (!valor1.equals(valor2)){
        erroEditText(editText,MSG_ERRO_CAMPOS_DIFERENTES)
        return true
    }
    return false
}

fun esconderTeclado(activity: Activity) {
    try {
        val view = activity.currentFocus
        if (view != null) {
            val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }
    } catch (e: Exception) {
        Log.i(TAG, "esconder teclado " + e.message)
    }
}

fun Context.verificacaoOnFailedApi(erroOnFalidApi: Throwable){
    InternetCheck(object : InternetCheck.Consumer {
        override fun accept(internet: Boolean?) {
            if (internet != true) {
                if (internet != true) {
                    mostrarMensagem(msgSemInternet)
                } else if ("timeout" === erroOnFalidApi.message) {
                    mostrarMensagem(msgTimeOut)
                } else {
                    mostrarMensagem(msgAlgumProblema)
                }
            }
        }
    })
}

internal class InternetCheck(private val mConsumer: Consumer) : AsyncTask<Void, Void, Boolean>() {
    interface Consumer {
        fun accept(internet: Boolean?)
    }

    init {
        execute()
    }

    override fun doInBackground(vararg voids: Void): Boolean? {
        try {
            val sock = Socket()
            sock.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            sock.close()
            return true
        } catch (e: IOException) {
            return false
        }

    }

    override fun onPostExecute(internet: Boolean?) {
        mConsumer.accept(internet)
    }
}