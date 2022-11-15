package com.nsw2022.retrofitlibapp_ko

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nsw2022.retrofitlibapp_ko.databinding.RecycelrItemBinding
import com.nsw2022.retrofitlibapp_ko.databinding.RecyclerFavItemBinding

class FavRecyclerAdapter constructor(var context: Context,var items:MutableList<FavRecycelrItem>):RecyclerView.Adapter<FavRecyclerAdapter.VH>(){
    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding:RecyclerFavItemBinding = RecyclerFavItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView:View = LayoutInflater.from(context).inflate(R.layout.recycler_fav_item,parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {


    }

    override fun getItemCount(): Int = items.size

}