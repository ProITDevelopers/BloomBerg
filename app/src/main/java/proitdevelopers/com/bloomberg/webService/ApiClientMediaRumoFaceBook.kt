package proitdevelopers.com.bloomberg.webService

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClientMediaRumoFaceBook {

    //URL_BASE BASE
    private const val URL_BASE = "https://e795cdce.ngrok.io/v1/app/"

    //Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //Creat OkHttp Client
    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

    //create retrofit builder
    val builder = Retrofit.Builder().baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
        .build()

    //val retrofit = builder.build()

    fun <T> buildSercice (serviceType: Class<T> ): T{
        return builder.create(serviceType)
    }

}