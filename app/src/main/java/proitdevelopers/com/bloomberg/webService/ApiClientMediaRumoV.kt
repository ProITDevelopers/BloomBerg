package proitdevelopers.com.bloomberg.webService

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.net.ssl.*
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit

class ApiClientMediaRumoV {

    companion object {

    private val BASE_URL = "https://console.proitappsolutions.com/v1/app/"
    private var retrofit: Retrofit? = null

    private val okHttpClientvalor = OkHttpClient.Builder()
        .connectTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .build()

    fun  apiClient(): Retrofit {

        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClientvalor)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    }

}
