package com.test.trendinggitrepo.network

/**
 * Created by alexgomes on 2018-02-05.
 */
interface OnResponseListener<Any> {

    fun onSuccess(response: Any)

    fun onFailure(failure: String)
}

