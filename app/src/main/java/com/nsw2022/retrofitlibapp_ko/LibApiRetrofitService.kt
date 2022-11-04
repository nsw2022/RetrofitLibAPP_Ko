package com.nsw2022.retrofitlibapp_ko

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LibApiRetrofitService {

    @GET("{api_key}/json/SeoulLibraryTimeInfo/1/1000/")
    fun getAPILibDatas(@Path("api_key")key:String):Call<LibApiItem>
}