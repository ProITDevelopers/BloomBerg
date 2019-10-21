package proitdevelopers.com.bloomberg.basededados.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import proitdevelopers.com.bloomberg.basededados.daos.NoticiaDao
import proitdevelopers.com.bloomberg.basededados.entitys.Noticia

@Database(entities = [Noticia::class],version = 1)
abstract class DbMediaRumo:RoomDatabase() {

    abstract fun noticiaDao():NoticiaDao

    companion object{
        @Volatile
        private var dbMediaRumoInstancia : DbMediaRumo? = null

        internal fun getDataBase(context: Context):DbMediaRumo?{
            if (dbMediaRumoInstancia == null){
                synchronized(DbMediaRumo::class.java){
                    if (dbMediaRumoInstancia == null){
                        dbMediaRumoInstancia = Room.databaseBuilder(
                            context.applicationContext,
                            DbMediaRumo::class.java,"db_media_rumo")
                            .build()
                    }
                }
            }
            return dbMediaRumoInstancia
        }
    }
}