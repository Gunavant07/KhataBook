package com.example.khatabook.activity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.fragment.app.FragmentActivity
import com.example.khatabook.DataClass.ModelData

class DBHelper(context: FragmentActivity?): SQLiteOpenHelper(context, "Khatabook.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var query = "CREATE TABLE khatabook(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,mobile TEXT,item TEXT,quantity TEXT,amount TEXT,status TEXT,date TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    fun insertData(n1:String,m1:String,i1:String,q1:String,a1:String,d1:String,s1:String){

        var db =writableDatabase

        var contentValue = ContentValues()
        contentValue.put("name",n1)
        contentValue.put("mobile",m1)
        contentValue.put("item",i1)
        contentValue.put("quantity",q1)
        contentValue.put("amount",a1)
        contentValue.put("date",d1)
        contentValue.put("status",s1)

        db.insert("khatabook",null,contentValue)
    }
    @SuppressLint("Range")
    fun ReadData():ArrayList<ModelData>{

        var db =readableDatabase
        var query = "SELECT * FROM khatabook"
        var cursor=db.rawQuery(query,null)
        var list= arrayListOf<ModelData>()
        if(cursor.moveToFirst())
        {
            do{
                var id = cursor.getString(cursor.getColumnIndex("id")).toString()
                var name = cursor.getString(cursor.getColumnIndex("name")).toString()
                var mobile =cursor.getString(cursor.getColumnIndex("mobile")).toString()
                var item = cursor.getString(cursor.getColumnIndex("item")).toString()
                var quantity = cursor.getString(cursor.getColumnIndex("quantity")).toString()
                var amount = cursor.getString(cursor.getColumnIndex("amount")).toString()
                var date = cursor.getString(cursor.getColumnIndex("date")).toString()
                var status = cursor.getString(cursor.getColumnIndex("status")).toString()


                var d1 = ModelData(id,name,mobile,item,quantity,amount,date,status)
                list.add(d1)
            }while (cursor.moveToNext())
        }
        return list
    }
    fun delete(id :String){
        var db = writableDatabase
        db.delete("khatabook","id = $id",null)
    }
    fun update(id: String,n1:String,m1:String,i1:String,q1:String,a1:String,d1:String){
        var db =writableDatabase

        var contentValue = ContentValues()
        contentValue.put("id",id)
        contentValue.put("name",n1)
        contentValue.put("mobile",m1)
        contentValue.put("item",i1)
        contentValue.put("quantity",q1)
        contentValue.put("amount",a1)
        contentValue.put("date",d1)
        db.update("khatabook",contentValue,"id = $id",null)
    }
}