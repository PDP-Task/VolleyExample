package com.samsdk.networkingvolley.network

interface VolleyHandler {
    fun onSuccess(response: String?)
    fun onError(error: String?)
}