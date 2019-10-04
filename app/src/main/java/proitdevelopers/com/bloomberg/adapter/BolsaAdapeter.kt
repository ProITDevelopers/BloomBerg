package proitdevelopers.com.bloomberg.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_lista_fragment_bolsa_um.view.*
import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.modelo.Bolsa

class BolsaAdapeter(private val context:Context,private val bolsas:List<Bolsa >) : RecyclerView.Adapter<BolsaAdapeter.MyViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_fragment_bolsa_um,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bolsas.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, posicao: Int) {
        val bolsas = bolsas[posicao]
        holder.mudarDados(bolsas)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun mudarDados(bolsas:Bolsa?){
            itemView.servico_tv.text = bolsas!!.servico
            itemView.valor_tv.text = bolsas.valor
            itemView.variacao_tv.text = bolsas.variacao
        }
    }

}