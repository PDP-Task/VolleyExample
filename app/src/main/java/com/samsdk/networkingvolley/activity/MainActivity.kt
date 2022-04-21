package com.samsdk.networkingvolley.activity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.samsdk.networkingvolley.adapter.VolleyAdapter
import com.samsdk.networkingvolley.databinding.ActivityMainBinding
import com.samsdk.networkingvolley.model.Post
import com.samsdk.networkingvolley.network.VolleyHandler
import com.samsdk.networkingvolley.network.VolleyHttp

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var requestQueue: RequestQueue
    private lateinit var volleyAdapter: VolleyAdapter
    private val posters = ArrayList<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        apiPosterList()
        refreshAdapter(posters)
    }

    private fun apiPosterList() {
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), @SuppressLint("NotifyDataSetChanged")
        object : VolleyHandler {
            override fun onSuccess(response: String?) {
                val posterArray = Gson().fromJson(response, Array<Post>::class.java)
                posters.addAll(posterArray)
                volleyAdapter.notifyDataSetChanged()
            }

            override fun onError(error: String?) {
                Log.d("@@@", error!!)
            }
        })
    }

    private fun refreshAdapter(posters: ArrayList<Post>) {
        volleyAdapter = VolleyAdapter(this, posters)
        binding.recyclerView.adapter = volleyAdapter
    }
}
/*
private fun fetchImageLoad() {
    val imageRequest = ImageRequest(
        "https://i.imgur.com/Nwk25LA.jpg",
        {
            binding.imageView.setImageBitmap(it)
        },
        0, 0, ImageView.ScaleType.CENTER_CROP,
        Bitmap.Config.ARGB_8888
    ) {
        binding.textView.text = it?.message
    }
    requestQueue.add(imageRequest)
}

private fun isNetworkConnected(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        networkCapabilities != null && networkCapabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        )
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
}
 */