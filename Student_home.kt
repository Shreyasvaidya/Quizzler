package com.example.quizzler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Student_home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_home)

        val button = findViewById<Button>(R.id.my_start_button)



        button.setOnClickListener{
            intent = Intent(this,Quiz::class.java)
            startActivity(intent)
        }
    }
}