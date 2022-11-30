package com.nsw2022.retrofitlibapp_ko

import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.nsw2022.retrofitlibapp_ko.databinding.ActivityReviewFromHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ReviewActivityFromHome : AppCompatActivity() {
    lateinit var binding: ActivityReviewFromHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityReviewFromHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        binding.tvNameLibReview.text=intent.getStringExtra("title")
        binding.tvGuLibReview.text=intent.getStringExtra("gu")
        binding.tvHolidayReview.text=intent.getStringExtra("holiday")
        binding.tvTelLibReview.text=intent.getStringExtra("tel")

        loadData()

        binding.btnBackPress.setOnClickListener { onBackPressed() }
        binding.btnSave.setOnClickListener { clickComplete() }



    }//OnCrate

    fun loadData(){
        val pref=getSharedPreferences("account", MODE_PRIVATE)
        val userName = pref.getString("username","")
        val userprofile = pref.getString("porfile","")
    }




    fun clickComplete(){
        var pref = getSharedPreferences("account", MODE_PRIVATE)
        val profile = pref.getString("porfile","")
        val name = pref.getString("username","")

        val libContent=binding.etContent.text.toString()
        var title=binding.tvNameLibReview.text.toString()

        //AlertDialog.Builder(this).setMessage("프로필 : $profile\n,사용자이름 : $name\n,도서관이름 : $libName\n,도서관 내용 : $libContent").create().show()

        val retofit:Retrofit = Retrofit.Builder()
            .baseUrl("http://tmddn34101.dothome.co.kr/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retofit.create(ReviewRetrofit::class.java)

        var dataPart:MutableMap<String, String> = mutableMapOf()
        dataPart["title"] = title
        dataPart["user_name"] = name!!
        dataPart["msg"] = libContent
        dataPart["img"] = profile!!


        val call=retrofitService.postDataToServer( dataPart )

        call.enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                response.body()?.let{
                    Log.d("MSGG",it)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("MSGGG",t.message!!)
            }

        })





        onBackPressed()
        Toast.makeText(this, "저장완료!", Toast.LENGTH_SHORT).show()
    }
}