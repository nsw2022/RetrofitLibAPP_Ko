package com.nsw2022.retrofitlibapp_ko


import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.FileObserver.CREATE
import android.util.Log

class FavDB (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, 1){
    companion object{
        const val DATABASE_NAME = "LibraryDB"
        const val TABLE_NAME = "favoriteTable"
        //PK num Int(자동증가), name char(20), address(40), hoilday(40), tel(40), fStatus int(1=클릭됨,0클릭안됨)
        var num = "num"
        var ITEM_TITLE = "itemTitle"
        var ITEM_ADDRESS = "itmeAddress"
        var ITEM_HOILDAY = "item_hoilday"
        var ITEM_GU = "item_gu"
        var ITEM_TEL = "item_tel"
        var FAVORITE_STATUS = "f_status"
        var SEQ_NO = "item_seq_no"

        private val CREATE_CABLE = ("CREATE TABLE IF NOT EXISTS" + TABLE_NAME + "("
                + num + " INTEGER PRIMARY KEY AUTOINCREMENT" + ITEM_TITLE + " TEXT," + ITEM_ADDRESS + " TEXT"
                + ITEM_HOILDAY + " TEXT" + ITEM_GU + " TEXT" + ITEM_TEL + " TEXT" + FAVORITE_STATUS + " TEXT" + SEQ_NO + " TEXT")


    }
    //onCreate(): 앱이 설치된 후 SQLiteOpenHelper가 최초로 이용되는 순간 한 번 호출
    //onUpgrade(): 데이터베이스 버전이 변경될 때마다 호출
    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        sqLiteDatabase?.execSQL(CREATE_CABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    // insert line from database
    fun insertIntoTheDatabase(
        itemTitle : String,
        iTEM_ADDRESS : String,
        iTEM_HOILDAY : String,
        iTEM_GU : String,
        iTEM_TEL : String,
        FAVORITE_STATUS : String,
        SEQ_NO : String
    ){
        val db: SQLiteDatabase
        db = this.writableDatabase
        val cv = ContentValues()
        cv.put(ITEM_TITLE,itemTitle)
        db.insert(TABLE_NAME,null,cv)
        Log.i("TAG","$itemTitle, 나는 도서관이름이야! $cv 안녕 나는 입력한 값이야!")
    }

    // remove line from database
    fun remove_fav(item_seq_no: String) {
        val db = this.writableDatabase
        val sql =
            "UPDATE $TABLE_NAME SET  $FAVORITE_STATUS ='0' WHERE$SEQ_NO=$item_seq_no"
        db.execSQL(sql)
        Log.d("remove", item_seq_no)
    }

}