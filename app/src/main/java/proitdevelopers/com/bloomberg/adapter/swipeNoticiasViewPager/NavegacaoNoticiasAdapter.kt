package proitdevelopers.com.bloomberg.adapter.swipeNoticiasViewPager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Noticia

class NavegacaoNoticiasAdapter(val context: Context,val listaNoticias:List<Noticia>):RecyclerView.Adapter<NavegacaoNoticiasAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view_pager_noticias,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = listaNoticias.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }


    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

}