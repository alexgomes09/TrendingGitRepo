package com.test.trendinggitrepo.network

import com.test.trendinggitrepo.model.RepoResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by alexgomes on 2018-02-05.
 */
interface ApiService {

    @GET("/search/repositories?q=android+language:java&sort=stars")
    fun listRepo(): Call<RepoResponse>
}