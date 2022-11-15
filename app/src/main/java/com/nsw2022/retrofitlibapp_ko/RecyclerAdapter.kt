package com.nsw2022.retrofitlibapp_ko

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.MODE_PRIVATE
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ContextUtils.getActivity
import com.nsw2022.retrofitlibapp_ko.databinding.RecycelrItemBinding

class RecyclerAdapter constructor(var context: Context,var items:MutableList<Row>): RecyclerView.Adapter<RecyclerAdapter.VH>(){
    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding:RecycelrItemBinding = RecycelrItemBinding.bind(itemView)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView:View=LayoutInflater.from(context).inflate(R.layout.recycelr_item,parent,false)



        return VH(itemView)
    }



    override fun onBindViewHolder(holder: VH, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.tvNameLib.text=items[position].LBRRY_NAME
        holder.binding.tvGuaddressLib.text=items[position].CODE_VALUE
        holder.binding.tvHolidayLib.text=items[position].FDRM_CLOSE_DATE
        holder.binding.tvTelLib.text=items[position].TEL_NO
        holder.binding.tvAddressLib.text=items[position].ADRES


        holder.binding.tbFav.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(comButton: CompoundButton?, isCheck: Boolean) {
                if (isCheck){

                    //val db: SQLiteDatabase = FavDB(context).writableDatabase



                }else{


                }

            }
        })//clickListener/////////////////////
    }//OnBindView//////////////////////////////////////////////////////////////////////////////////////////////////

    override fun getItemCount(): Int = items.size



}