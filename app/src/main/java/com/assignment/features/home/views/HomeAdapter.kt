package com.assignment.features.home.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.features.R
import com.bumptech.glide.Glide
import com.assignment.network.model.Users
import javax.inject.Inject


class HomeAdapter @Inject constructor(): RecyclerView.Adapter<HomeAdapter.NetworkDataViewHolder>() {

    private var networkDataList = mutableListOf<Users>()
    private var context: Context? = null
    private var layoutInflater: LayoutInflater? = null

    fun addItemList(networkDataList: MutableList<Users>) {
        this.networkDataList = networkDataList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkDataViewHolder {

        if (layoutInflater == null) {
            context = parent.context
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val itemView = layoutInflater!!
            .inflate(R.layout.layout_user_list, parent, false)
        return NetworkDataViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: NetworkDataViewHolder, position: Int) {
        val item = networkDataList[position]
        holder.tvLayoutName.text = item.name
        context?.let { it ->
            holder.ivUserImage.let { it1 ->
                Glide.with(it).load(item.image).into(it1)
            }
        }
    }

    override fun getItemCount(): Int {
        return networkDataList.size
    }


    class NetworkDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvLayoutName:TextView = view.findViewById(R.id.tvLayoutName)
        val ivUserImage:ImageView = view.findViewById(R.id.ivUserImage)
    }
}
