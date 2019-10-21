package proitdevelopers.com.bloomberg.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import proitdevelopers.com.bloomberg.basededados.bd.DbMediaRumo
import proitdevelopers.com.bloomberg.basededados.daos.NoticiaDao
import proitdevelopers.com.bloomberg.basededados.entitys.Noticia

class NoticiaViewModel(application:Application):AndroidViewModel(application) {

    private val noticiaDao:NoticiaDao
    internal val todasNoticiasFavoritas:LiveData<List<Noticia>>

    init {
        val dbMediaRumo = DbMediaRumo.getDataBase(application)
        noticiaDao = dbMediaRumo!!.noticiaDao()
        todasNoticiasFavoritas = noticiaDao.todasNoticias
    }

    fun insertNoticiasFavoritas(noticia:Noticia){
        InsertAsyncTask(noticiaDao).execute(noticia)
    }

    fun deleteNoticiaFavorita(noticia: Noticia){
        DeleteAsyncTask(noticiaDao).execute(noticia)
    }

    companion object{
        private class InsertAsyncTask(private val mNoticiaDao: NoticiaDao):AsyncTask<Noticia,Void,Void>(){
            override fun doInBackground(vararg noticia: Noticia): Void? {
                mNoticiaDao.insertNoticiasFavoritas(noticia[0])
                return null
            }
        }

        private class DeleteAsyncTask(private val mNoticiaDao:NoticiaDao): AsyncTask<Noticia,Void,Void>(){
            override fun doInBackground(vararg noticia: Noticia): Void? {
                mNoticiaDao.delete(noticia[0])
                return null
            }
        }
    }

}