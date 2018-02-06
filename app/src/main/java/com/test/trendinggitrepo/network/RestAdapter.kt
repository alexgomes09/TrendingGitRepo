package com.test.trendinggitrepo.network

import android.app.Activity
import com.test.trendinggitrepo.BuildConfig
import com.test.trendinggitrepo.model.RepoResponse
import com.test.trendinggitrepo.util.AppUtil
import com.test.trendinggitrepo.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Created by alexgomes on 2018-02-05.
 */
object RestAdapter {

    private var apiService: ApiService

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(Constants.networkTimeOut, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(Constants.networkTimeOut, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(Constants.networkTimeOut, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }

        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build()

        apiService = retrofit.create(ApiService::class.java)
    }


    private class CustomCallback<T> internal constructor(internal var onResponseListener: OnResponseListener<RepoResponse>) : Callback<T> {

        override fun onResponse(call: retrofit2.Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                onResponseListener.onSuccess(response.body() as RepoResponse)
            } else {
                try {
                    onResponseListener.onFailure("Encountered server error. Please try again shortly")
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        override fun onFailure(call: retrofit2.Call<T>, t: Throwable) {
            t.message?.let { onResponseListener.onFailure(it) }
        }
    }

    fun GetTrendingAndroid(context: Activity, onResponseListener: OnResponseListener<RepoResponse>) {
        //we check internet connection before making every network call
        if (!AppUtil.checkForInternet(context)) return

        val repoList: Call<RepoResponse> = apiService.listRepo()
        repoList.enqueue(CustomCallback<RepoResponse>(onResponseListener))
    }
}