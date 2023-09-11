package com.example.assessment_4662585_4662587

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager.LayoutParams.*
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // After clicking ADD CAR it will direct to the next activity
        findViewById<Button>(R.id.btnAddCar).setOnClickListener {
            val intent = Intent(this, AddCarActivity::class.java)
            startActivity(intent)
        }

        //After clicking CHECK CAR it will direct to the next activity
        findViewById<Button>(R.id.btnCheckCar).setOnClickListener {
            val intent = Intent(this, CheckCarActivity::class.java)
            startActivity(intent)
        }
    }
}