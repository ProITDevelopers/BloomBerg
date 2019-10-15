package proitdevelopers.com.bloomberg.webService

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CambiosBancoEuropaServiceBuilder {

    //URL BASE
    private const val URL = "https://api.exchangeratesapi.io/"

    //Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //okHttp Cliente
    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

    //Retrofit builder
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit = builder.build()

    fun <T> buildSercice (serviceType: Class<T> ): T{
        return retrofit.create(serviceType)
    }

}