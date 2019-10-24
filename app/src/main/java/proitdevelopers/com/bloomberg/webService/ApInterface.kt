package proitdevelopers.com.bloomberg.webService

import proitdevelopers.com.bloomberg.modelo.Data
import proitdevelopers.com.bloomberg.modelo.Usuario
import retrofit2.Call
import retrofit2.http.*

interface ApInterface {

    @POST("/v1/app/")
    fun registrarCliente(@Body usuario: Usuario):Call<Void>

    @FormUrlEncoded
    @POST("/v1/app/login")
    fun autenticarCliente(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Data>

    //oauth/facebook
    @FormUrlEncoded
    @POST("oauth/facebook")
    fun autenticarFacebook(
        @Field("access_token") access_token:String
    ):Call<Data>

}