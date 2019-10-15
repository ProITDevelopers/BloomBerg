package proitdevelopers.com.bloomberg.modelo.webServicesApi

import com.google.gson.annotations.SerializedName

class CambiosRatesParent {

    @field:SerializedName("start_at")
    var data_inicio:String? = null

    @field:SerializedName("base")
    val base:String? = null

    @field:SerializedName("end_at")
    val data_fim:String? = null

    @field:SerializedName("rates")
    val Rates : Rates ? = null

}

class Rates{
    @field:SerializedName("date")
    val data:String? = null
}