package proitdevelopers.com.bloomberg.webService.erroApi

import okhttp3.ResponseBody
import proitdevelopers.com.bloomberg.webService.ApiClientMediaRumoV
import retrofit2.Converter
import retrofit2.Response

import java.io.IOException

object ErrorUtils {

    fun parseError(response: Response<*>): ErrorResponce? {
        val conversorDeErro = ApiClientMediaRumoV.apiClient()
            .responseBodyConverter<ErrorResponce>(ErrorResponce::class.java, arrayOfNulls(0))
        var errorResponce: ErrorResponce? = null
        try {
            if (response.errorBody() != null) {
                errorResponce = conversorDeErro.convert(response.errorBody()!!)
            }
        } catch (e: IOException) {
            return ErrorResponce()
        } finally {
            return errorResponce
        }
    }
}