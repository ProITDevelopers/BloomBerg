package proitdevelopers.com.bloomberg.dbConnection;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import proitdevelopers.com.bloomberg.modelo.Revistas;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {


    @GET("/v1/app/artigo")
    Call<List<Revistas>> getRevistas();
}
