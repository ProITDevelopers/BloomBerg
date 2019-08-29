package proitdevelopers.com.bloomberg.communs

import android.view.View.GONE
import android.widget.ImageView

fun esconderSeparador(imgSeparador:ImageView,posicao:Int,limite:Int){
    if (posicao==limite-1)
        imgSeparador.visibility = GONE
}