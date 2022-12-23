package com.nsw2022.retrofitlibapp_ko

data class ReviewItem constructor(
    var no:Int,
    var userName:String,
    var userProfile:String,
    var LibName:String,
    var content:String,
    var date:String
)