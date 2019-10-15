package proitdevelopers.com.bloomberg.adapter.menu_sessoes

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.item_recycler_view_menu_sub.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.ItemMenu

class MenuSessoesSubAdapeter(
    private val context: Context,
    private val menus: List<ItemMenu>,
    private val menu_img: Int
) :
    RecyclerView.Adapter<MenuSessoesSubAdapeter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_menu_sub, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = menus.size

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        val menu = menus[posicao]
        holder.mudarDados(menu, posicao)

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun mudarDados(itemMenu: ItemMenu, posicao: Int) {
            itemView.item_menu_recycler.text = itemMenu.itemMenu
            itemView.item_menu_img.setImageResource(menu_img)

            if (posicao != 0)
                itemView.item_menu_img.visibility = View.INVISIBLE

            if (posicao == menus.size - 1) {
                itemView.item_re_separador.visibility = View.GONE
            }

            onClickItens(itemMenu)
        }

        fun onClickItens(itemMenu: ItemMenu) {
            itemView.setOnClickListener {
                Toast.makeText(context, " ${itemMenu.itemMenu}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}