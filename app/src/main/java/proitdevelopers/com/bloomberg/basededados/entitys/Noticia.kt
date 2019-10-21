package proitdevelopers.com.bloomberg.basededados.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noticiaFavorita")
class Noticia (

    @field:PrimaryKey
    var id: String,
    val titulo:String,
    val descricao: String,
    val conteudo: String,
    val foto: String,
    val video: String,
    val data: String,
    val fonte: String,
    val categoria: String,
    val duracao: String
)