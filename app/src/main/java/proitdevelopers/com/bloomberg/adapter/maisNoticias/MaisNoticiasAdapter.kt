package proitdevelopers.com.bloomberg.adapter.maisNoticias

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_lista_top_news.view.*
import kotlinx.android.synthetic.main.item_mais_noticias.view.*
import kotlinx.android.synthetic.main.item_noticias_lado_a_lado.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Noticia

class MaisNoticiasAdapter(val context: Context, val noticias: List<Noticia>) :
    RecyclerView.Adapter<MaisNoticiasAdapter.MyViewHolder>() {

    var indexLista_ = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_mais_noticias, parent, false)
        view.imgSeparador.visibility = View.GONE
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = noticias.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mudarNoticiaUI()
        Log.i("TESTEPOSICAO", "$position")
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun mudarNoticiaUI() {
            if (indexLista_ < noticias.size) {
                Glide.with(context)
                    .load(noticias[indexLista_].foto)
                    .into(itemView.mais_not_img_dest)
                itemView.mais_not_titulo_dest.text = noticias[indexLista_].titulo
                itemView.mais_not_data_dest.text = noticias[indexLista_].data
                itemView.groupItensNoticias.visibility = View.VISIBLE
                //itemView.mais_not_partilha_dest
                //itemView.mais_not_favorito_dest
            }
            // itemView.groupItensNoticias.visibility = View.GONE


                if (indexLista_ + 1 < noticias.size) {
                    //------------------
                    Glide.with(context)
                        .load(noticias[indexLista_ + 1].foto)
                        .into(itemView.item_sub_not_img)
                    itemView.item_tendencias_categoria.text = noticias[indexLista_ + 1].categoria
                    itemView.item_tendencias_titulo.text = noticias[indexLista_ + 1].titulo
                    itemView.item_tendencias_data_pub.text = noticias[indexLista_ + 1].data
                    itemView.groupItensNoticias.visibility = View.VISIBLE
                    //itemView.item_sub_not_ic_partilha
                    //itemView.item_sub_not_ic_favorito
                }
                // itemView.groupItensNoticias.visibility = View.GONE

                //-----------------
                    if (indexLista_ + 2 < noticias.size) {
                        itemView.noticia_lado_cat1.text = noticias[indexLista_ + 2].categoria
                        itemView.noticia_lado_titulo1.text = noticias[indexLista_ + 2].titulo
                        itemView.noticia_lado_datap1.text = noticias[indexLista_ + 2].data
                        itemView.groupItensNoticias.visibility = View.VISIBLE
                        //itemView.noticia_lado_ic_part1
                        //itemView.noticia_lado_ic_fav1
                    }
                    //  itemView.groupItensNoticias.visibility = View.GONE


                        if (indexLista_ + 3 < noticias.size) {
                            //---------
                            itemView.noticia_lado_cat2.text = noticias[indexLista_ + 3].categoria
                            itemView.noticia_lado_titulo2.text = noticias[indexLista_ + 3].titulo
                            itemView.noticia_lado_datap2.text = noticias[indexLista_ + 3].data
                            itemView.groupItensNoticias.visibility = View.VISIBLE
                            //itemView.noticia_lado_ic_part2
                            //itemView.noticia_lado_ic_fav2
                        }
                        //  itemView.groupItensNoticias.visibility = View.GONE

                            indexLista_ += 4


        }
    }

}