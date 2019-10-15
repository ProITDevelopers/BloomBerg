package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_bolsa.*

import proitdevelopers.com.bloomberg.R
import proitdevelopers.com.bloomberg.adapter.BolsaAdapeter
import proitdevelopers.com.bloomberg.interfaces.webServices.CambioService
import proitdevelopers.com.bloomberg.modelo.ValoresBolsa
import proitdevelopers.com.bloomberg.modelo.webServicesApi.CambiosRatesParent
import proitdevelopers.com.bloomberg.webService.CambiosBancoEuropaServiceBuilder
import retrofit2.Call
import retrofit2.Response

class BolsaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bolsa, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val adapter = activity?.let { BolsaAdapeter(it, ValoresBolsa.bolsas) }
        ViewCompat.setNestedScrollingEnabled(recyclerViewBolsas, false)
        recyclerViewBolsas.layoutManager = layoutManager
        recyclerViewBolsas.adapter = adapter

        //------------------------------------
        val service = CambiosBancoEuropaServiceBuilder.buildSercice(CambioService::class.java)
        val call = service.getRepositories()

       call.enqueue(object :retrofit2.Callback<CambiosRatesParent>{

           override fun onResponse(call: Call<CambiosRatesParent>, response: Response<CambiosRatesParent>) {
               if(response.isSuccessful){
                   val apiResponse = response.body()!!
                   val responseItems = apiResponse.Rates

                   val size = responseItems?.let { responseItems}
                   Log.i("falja", " tamanho lista $size")
                   Log.i("falja", " ${responseItems!!.data}")
                   Log.i("falja", " ${responseItems.toString()}")
               }else{
                   Log.i("falja", " ${response.errorBody().toString()}")
               }
           }

           override fun onFailure(call: Call<CambiosRatesParent>, t: Throwable) {
               Log.i("falja",t.toString())
           }
       })
    }

}
