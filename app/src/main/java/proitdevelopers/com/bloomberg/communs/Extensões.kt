package proitdevelopers.com.bloomberg.communs

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View.GONE
import android.widget.ImageView

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
