package com.example.yemeksiparisapp.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.yemeksiparisapp.data.entity.restaurants.Foodmenu

val table_name="FoodBasket"
val col_foodname="foodName"
val col_price="foodPrice"
val col_foodimg="foodImg"
val col_foodId="foodId"

class SqLiteDbManager(context: Context): SQLiteOpenHelper(context,"orders",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = " CREATE TABLE IF NOT EXISTS " + table_name + "(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                col_foodname + " TEXT," +
                col_foodimg + " TEXT," +
                col_foodId + " TEXT," +
                col_price + " TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addFoodtoBasket(food: Foodmenu) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(col_foodname, food.foodname)
        values.put(col_foodimg, food.foodimg)
        values.put(col_foodId, food.id)
        values.put(col_price, food.price)
        db.insert(table_name, null, values)
        db.close()
    }

    fun getAllBasketFoods(): ArrayList<Foodmenu> {
        val list = ArrayList<Foodmenu>()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + table_name
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val foodid = cursor.getString(cursor.getColumnIndex(col_foodId))
            val foodname = cursor.getString(cursor.getColumnIndex(col_foodname))
            val foodimg = cursor.getString(cursor.getColumnIndex(col_foodimg))
            val foodprice = cursor.getString(cursor.getColumnIndex(col_price))
            var food = Foodmenu(foodimg,foodname,foodid,foodprice)
            list.add(food)
        }
        db.close()
        return list
    }

    fun deleteBasketFoodById(id: String) {
        val db = this.writableDatabase
        db.delete(table_name, "foodId=?", arrayOf(id.toString()))
        db.close()
    }
}