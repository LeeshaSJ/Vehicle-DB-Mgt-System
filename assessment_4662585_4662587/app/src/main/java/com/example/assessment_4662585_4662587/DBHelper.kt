package com.example.assessment_4662585_4662587

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*
import kotlin.concurrent.schedule

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    //this method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_1 + " VARCHAR, " +
                COLUMN_2 + " VARCHAR," +
                COLUMN_3 + " VARCHAR" + ")")

        // method for executing the query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // This method is for adding data to database
    fun addCar(brand: String, model: String, price: String) {

        // a content values variable
        val values = ContentValues()

        // Inserting values in the form of key-value pair
        values.put( COLUMN_1, brand)
        values.put( COLUMN_2, model)
        values.put( COLUMN_3, price)


        // This is the writable variable of
        // database to insert value in the database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        //The database is closed after 10sec to view database at app inspection
        Timer().schedule(10000){
            db.close()
        }
    }

    // below method is to get all data from the database
    fun getPrice(brand: String, model: String): Cursor? {

        // readable variable of the database to read value from it
        val db = this.readableDatabase

        // This code returns a cursor to read data from the database
        return db.rawQuery("SELECT price FROM $TABLE_NAME WHERE brand = '$brand' AND model = '$model'", null)
    }

    companion object {
        // variables for the database
        // THis is variable for database name
        private const val DATABASE_NAME = "garage"

        // This is the variable for database version
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "car"
        const val COLUMN_1 = "brand"
        const val COLUMN_2 = "model"
        const val COLUMN_3 = "price"
    }
}

