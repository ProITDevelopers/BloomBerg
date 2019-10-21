package proitdevelopers.com.bloomberg.basededados.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import proitdevelopers.com.bloomberg.basededados.entitys.Noticia

@Dao
interface NoticiaDao {

    @Insert
    fun insertNoticiasFavoritas(noticia: Noticia)

    @get:Query("SELECT * FROM noticiaFavorita")
    val todasNoticias:LiveData<List<Noticia>>

    @Delete
    fun delete(noticia: Noticia)

}