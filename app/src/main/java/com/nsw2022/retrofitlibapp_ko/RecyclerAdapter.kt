package com.nsw2022.retrofitlibapp_ko

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nsw2022.retrofitlibapp_ko.databinding.RecycelrItemBinding

class RecyclerAdapter constructor(var context: Context,var items:MutableList<Row>): RecyclerView.Adapter<RecyclerAdapter.VH>(){
    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding:RecycelrItemBinding = RecycelrItemBinding.bind(itemView)
    }


    lateinit var favDB: FavDB
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

                    favDB= FavDB(context)
                    var item_title=items[position].LBRRY_NAME
                    var item_add=items[position].ADRES
                    var item_hoilday=items[position].FDRM_CLOSE_DATE
                    var item_gu=items[position].CODE_VALUE
                    var item_tel=items[position].TEL_NO
                    var item_fstaus="1"
                    var item_seq_no=items[position].LBRRY_SEQ_NO
                    favDB.insertIntoTheDatabase(item_title,item_add,item_hoilday,item_gu,item_tel,item_fstaus,item_seq_no)


                }else{


                }

            }
        })//clickListener/////////////////////
    }//OnBindView//////////////////////////////////////////////////////////////////////////////////////////////////

    override fun getItemCount(): Int = items.size



}