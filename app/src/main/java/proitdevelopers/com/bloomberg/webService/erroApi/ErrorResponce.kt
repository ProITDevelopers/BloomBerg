package proitdevelopers.com.bloomberg.webService.erroApi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorResponce {

    @SerializedName("error")
    @Expose
    var error: String? = null
}
