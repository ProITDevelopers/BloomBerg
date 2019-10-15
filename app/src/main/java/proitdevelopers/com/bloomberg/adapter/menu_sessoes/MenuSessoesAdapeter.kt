package proitdevelopers.com.bloomberg.adapter.menu_sessoes

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_recycler_view_menu.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.ItemMenu
import proitdevelopers.com.bloomberg.modelo.Menu

class MenuSessoesAdapeter(private val context:Context, private val menus:List<Menu >) :
    RecyclerView.Adapter<MenuSessoesAdapeter.MyViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_menu,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = menus.size

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        val menu = menus[posicao]
        holder.mudarDados(menu)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun mudarDados(menu: Menu){
            itemView.menu_sec_titulo.text = menu.tituloMenu
            OqueAssisirItemSubTercQuartAdapet(menu.itemMeu,menu.img, itemView.recycler_view_menu)
        }
    }

    private fun OqueAssisirItemSubTercQuartAdapet(
        menus: List<ItemMenu>,
        menu_img: Int,
        recyclerView: RecyclerView
    ) {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        val adapterConfQueAssistir =  MenuSessoesSubAdapeter(context,menus,menu_img)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterConfQueAssistir
    }
}