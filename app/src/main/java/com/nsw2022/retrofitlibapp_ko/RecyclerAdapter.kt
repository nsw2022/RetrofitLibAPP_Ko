package com.nsw2022.retrofitlibapp_ko

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter constructor(val context: Context,var items:MutableList<RecyclerItem>): RecyclerView.Adapter<RecyclerAdapter.VH>(){


    inner class VH constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvNameLib:TextView by lazy { itemView.findViewById(R.id.tv_name_lib) }
        val tvGuaddressLib:TextView by lazy { itemView.findViewById(R.id.tv_guaddress_lib) }
        val tvAddress:TextView by lazy { itemView.findViewById(R.id.tv_address_lib) }
        val tvHolidayLib:TextView by lazy { itemView.findViewById(R.id.tv_holiday_lib) }
        val tvTelLib:TextView by lazy { itemView.findViewById(R.id.tv_tel_lib) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater:LayoutInflater=LayoutInflater.from(context)
        var itemView:View= layoutInflater.inflate(R.layout.recycelr_item,parent,false)
        return VH(itemView)

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item:RecyclerItem=items.get(position)

        holder.tvNameLib.text=item.LBRRY_NAME
        holder.tvGuaddressLib.text=item.CODE_VALUE
        holder.tvAddress.text=item.ADRES
        holder.tvHolidayLib.text=item.FDRM_CLOSE_DATE
        holder.tvTelLib.text=item.TEL_NOL
    }

    override fun getItemCount(): Int = items.size

}