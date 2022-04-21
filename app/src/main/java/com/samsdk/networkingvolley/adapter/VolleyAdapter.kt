package com.samsdk.networkingvolley.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samsdk.networkingvolley.R
import com.samsdk.networkingvolley.databinding.ItemLayoutBinding
import com.samsdk.networkingvolley.model.Post

class VolleyAdapter(
    private val context: Context,
    private val items: ArrayList<Post>
) : RecyclerView.Adapter<VolleyAdapter.VolleyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VolleyViewHolder {
        return VolleyViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VolleyViewHolder, position: Int) {
        val post = items[position]

        holder.binding.apply {
            Glide.with(context)
                .load(post.avatar_url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)

            textLogin.text = post.login
            textType.text = post.type
            switchCompat.isChecked = post.site_admin
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class VolleyViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}