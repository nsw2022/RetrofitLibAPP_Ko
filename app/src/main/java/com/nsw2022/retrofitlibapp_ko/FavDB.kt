package com.nsw2022.retrofitlibapp_ko


import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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
        var ITEM_GU = "itemGu"
        var ITEM_TEL = "itemTel"
        var FAVORITE_STATUS = "fStatus"
        var SEQ_NO = "itemSeq"
        /*
        private val CREATE_TABLE = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + num + " INTEGER PRIMARY KEY AUTOINCREMENT " + ITEM_TITLE + " TEXT," + ITEM_ADDRESS + " TEXT,"
                + ITEM_HOILDAY + " TEXT," + ITEM_GU + " TEXT," + ITEM_TEL + " TEXT," + FAVORITE_STATUS + " TEXT," + SEQ_NO + " TEXT")
                ("CREATE TABLE IF NOT EXISTS LibraryDB(num INTEGER PRIMARY KEY AUTOINCREMENT, itemTitle TEXT, itmeAddress TEXT, itmeHoilday TEXT, itemGu Text, itemTel Text, fStatus Text, itemSeq Text)")
         */

        private val CRATE_TABLE = "(CREATE TABLE IF NOT EXISTS $DATABASE_NAME($num INTEGER PRIMARY KEY AUTOINCREMENT, $ITEM_TITLE TEXT, $ITEM_ADDRESS TEXT, $ITEM_HOILDAY TEXT, $ITEM_GU TEXT, $ITEM_TEL TEXT, $FAVORITE_STATUS TEXT, $SEQ_NO TEXT))"


    }
    //onCreate(): 앱이 설치된 후 SQLiteOpenHelper가 최초로 이용되는 순간 한 번 호출
    //onUpgrade(): 데이터베이스 버전이 변경될 때마다 호출
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CRATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    // insert line from database
    fun insertIntoTheDatabase(
        itemTitle : String,
        itmeAddress : String,
        iTEM_HOILDAY : String,
        iTEM_GU : String,
        iTEM_TEL : String,
        Fstsaus : String,
        itms_seq : String
    ){
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put(ITEM_TITLE,itemTitle)
        cv.put(ITEM_ADDRESS,itmeAddress)
        cv.put(ITEM_HOILDAY,iTEM_HOILDAY)
        cv.put(ITEM_GU,iTEM_GU)
        cv.put(ITEM_TEL,iTEM_TEL)
        cv.put(FAVORITE_STATUS,Fstsaus)
        cv.put(SEQ_NO,itms_seq)
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

    // select all favorite list
    fun select_all_favorite_list(): Cursor {
        val db = this.readableDatabase
        val sql = "SELECT * FROM $TABLE_NAME WHERE $FAVORITE_STATUS ='1'"
        return db.rawQuery(sql, null, null)
    }

}