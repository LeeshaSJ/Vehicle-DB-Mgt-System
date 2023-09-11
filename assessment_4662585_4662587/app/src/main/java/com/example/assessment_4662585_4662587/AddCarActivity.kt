package com.example.assessment_4662585_4662587

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddCarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Add Car"

        findViewById<Button>(R.id.btnAddCar).setOnClickListener {
            val brand = findViewById<EditText>(R.id.txtCarBrand)
            val model = findViewById<EditText>(R.id.txtCarModel)
            val price = findViewById<EditText>(R.id.txtCarPrice)

            // This is the DBHelper class to pass context to it.
            val db = DBHelper(baseContext, null)


            // Toast to messages on the screen
            if (brand.text.toString() == "" || model.text.toString() == "" || price.text.toString() == "") {
                Toast.makeText(this, "The data has not been added to database", Toast.LENGTH_LONG)
                    .show()
                it.hideKeyboard()
            } else {
                Toast.makeText(this, "Successfully added to database", Toast.LENGTH_LONG).show()
                //calling method to add data to database
                db.addCar(brand.text.toString(), model.text.toString(), price.text.toString())
                it.hideKeyboard()
            }
            //clearing edit texts
            brand.text.clear()
            model.text.clear()
            price.text.clear()
        }
    }
    //to hide keyboard after clicking ADD CAR button
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}