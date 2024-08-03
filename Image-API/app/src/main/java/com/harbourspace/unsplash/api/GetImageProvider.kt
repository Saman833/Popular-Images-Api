package com.harbourspace.unsplash.api


import com.harbourspace.unsplash.data.ImageDatas
import com.harbourspace.unsplash.data.sb.UnsplashResultt
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val BASE_URL = "https://api.unsplash.com/"

class GetImageProvider {

    private val retrofit by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create<UnsplashApi>()
    }

    fun getImageById(id: String , sb : UnsplashResultt) {
        retrofit.getImageById(id).enqueue(object :Callback<ImageDatas> {
            override fun onResponse(
                call: Call<ImageDatas>,
                response: Response<ImageDatas>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    sb.onDataFetchedSuccess(response.body()!!)
                } else {
                    sb.onDataFetchedFailed()
                }

            }

            override fun onFailure(call: Call<ImageDatas>, t: Throwable) {
                sb.onDataFetchedFailed()
            }
        })
    }
}

