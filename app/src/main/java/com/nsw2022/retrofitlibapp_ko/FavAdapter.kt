package com.nsw2022.retrofitlibapp_ko

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nsw2022.retrofitlibapp_ko.databinding.RecyclerFavItemBinding

class FavAdapter constructor(var context: Context, var itmes:MutableList<ReviewItem>):
    RecyclerView.Adapter<FavAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding:RecyclerFavItemBinding = RecyclerFavItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView:View=LayoutInflater.from(context).inflate(R.layout.recycler_fav_item,parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tvUserId.text=itmes[position].userName
        holder.binding.tvDate.text=itmes[position].date
        holder.binding.tvContext.text=itmes[position].content
        holder.binding.tvLibName.text=itmes[position].LibName

        Glide.with(context).load(itmes[position].userProfile).error(R.drawable.profle).into(holder.binding.civUser)
    }

    override fun getItemCount(): Int = itmes.size
}