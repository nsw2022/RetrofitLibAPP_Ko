package com.nsw2022.retrofitlibapp_ko

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ReviewRetrofit {

    @Multipart
    @POST("Lib/insertDB.php")
    fun postDataToServer(
        @PartMap dataPart: MutableMap<String, String>
//        @Part filePart: MultipartBody.Part?
    ): Call<String>

    @GET("Lib/loadDB.php")
    fun loadDataFromServer():Call<MutableList<ReviewItem>>

}