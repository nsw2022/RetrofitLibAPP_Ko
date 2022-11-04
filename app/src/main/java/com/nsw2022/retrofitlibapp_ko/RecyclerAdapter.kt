package com.nsw2022.retrofitlibapp_ko

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nsw2022.retrofitlibapp_ko.databinding.RecycelrItemBinding

class RecyclerAdapter constructor(var context: Context,var items:MutableList<Row>): RecyclerView.Adapter<RecyclerAdapter.VH>(){
    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding:RecycelrItemBinding = RecycelrItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView:View=LayoutInflater.from(context).inflate(R.layout.recycelr_item,parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tvNameLib.text=items[position].LBRRY_NAME
        holder.binding.tvGuaddressLib.text=items[position].CODE_VALUE
        holder.binding.tvHolidayLib.text=items[position].FDRM_CLOSE_DATE
        holder.binding.tvTelLib.text=items[position].TEL_NO
        holder.binding.tvAddressLib.text=items[position].ADRES
    }

    override fun getItemCount(): Int =items.size

}