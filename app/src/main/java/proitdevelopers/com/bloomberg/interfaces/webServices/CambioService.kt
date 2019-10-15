package proitdevelopers.com.bloomberg.interfaces.webServices

import proitdevelopers.com.bloomberg.modelo.webServicesApi.CambiosRatesParent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CambioService {


    @GET("history?start_at=2019-08-01&end_at=2019-10-14&symbols=USD")
    fun getRepositories(
        /*@Query("page") page:Int,
        @Query("per_page") size:Int,
        @Query("q") topic:String*/
    ): Call<CambiosRatesParent>
}