package com.example.assessment_4662585_4662587

import android.annotation.SuppressLint
import android.content.Context
import android.database.CursorIndexOutOfBoundsException
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CheckCarActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_car)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Check Car"

        val brand = findViewById<EditText>(R.id.txtCarBrand)
        val model = findViewById<EditText>(R.id.txtCarModel)
        val price = findViewById<TextView>(R.id.txtCarPrice)
        val db = DBHelper(baseContext, null)

        findViewById<Button>(R.id.btnCheckPrice).setOnClickListener {
            val cursor = db.getPrice(brand.text.toString(), model.text.toString())

            //Try catch used to stop crashing app, when user enter a wrong brand or model
            try {
                cursor!!.moveToFirst()
                price.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_3)) + "/=")
                it.hideKeyboard()

                while (cursor.moveToNext()) {
                    price.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_3)) + "/=")
                }
                cursor.close()

            } catch (e: CursorIndexOutOfBoundsException) {
                Toast.makeText(this, "Entry not found!", Toast.LENGTH_LONG).show()
                it.hideKeyboard()
                brand.text.clear()
                model.text.clear()
                price.text = ""
            }
        }
    }
    //to hide the keyboard after clicking the CHECK PRICE button
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
