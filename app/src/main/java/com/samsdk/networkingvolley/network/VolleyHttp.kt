package com.samsdk.networkingvolley.network

import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.samsdk.networkingvolley.app.MyApplication

object VolleyHttp {
    val TAG = VolleyHttp::class.java.simpleName
    val IS_TESTER = true
    val SERVER_DEVELOPMENT = "https://api.github.com/"
    val SERVER_PRODUCTION = "https://api.github.com/"

    fun server(url: String): String {
        if (IS_TESTER)
            return SERVER_DEVELOPMENT + url
        return SERVER_PRODUCTION + url
    }

    fun headers(): HashMap<String, String> {
        val headers = HashMap<String, String>()
        headers["Content-type"] = "application/json; charset=UTF-8"
        return headers
    }

    fun get(api: String, params: HashMap<String, String>, volleyHandler: VolleyHandler) {
        val stringRequest = object : StringRequest(
            Method.GET, server(api),
            Response.Listener { response ->
                volleyHandler.onSuccess(response)
            },
            Response.ErrorListener { error ->
                volleyHandler.onError(error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        MyApplication.instance!!.addToRequestQueue(stringRequest)
    }
    fun paramsEmpty(): HashMap<String, String> {
        return HashMap<String, String>()
    }
    var API_LIST_POST = "users"
}