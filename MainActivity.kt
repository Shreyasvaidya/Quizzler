package com.example.quizzler

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val register_btn = findViewById<Button>(R.id.home_register_button)
        register_btn.setOnClickListener {
            val switchActivityIntent = Intent(this, SignupActivity::class.java)
            startActivity(switchActivityIntent)
        }
        val login_stud_button = findViewById<Button>(R.id.home_student_button)
        login_stud_button.setOnClickListener{
            val switchActivityIntent = Intent(this, Student_login::class.java)
            startActivity(switchActivityIntent)
        }

    }


}
