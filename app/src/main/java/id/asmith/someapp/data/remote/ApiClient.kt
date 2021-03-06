package id.asmith.someapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import id.asmith.someapp.util.AppConstants as utils

/**
 * Created by Agus Adhi Sumitro on 23/12/2017.
 * https://asmith.my.id
 * aasumitro@gmail.com
 */

object ApiClient{
    private val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()

    private val auth = Retrofit.Builder()
            .baseUrl(utils.AUTH_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    private val user = Retrofit.Builder()
            .baseUrl(utils.USER_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    val authService: ApiService = auth.create(ApiService::class.java)
    val userService: ApiService = user.create(ApiService::class.java)
}