package com.example.quizzler

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import java.io.File
import java.io.FileReader
import com.opencsv.CSVReader;
import java.io.StringReader
import java.util.*


class Teacher_home : AppCompatActivity() {
    val ACTIVITY_CHOOSE_FILE1 = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_home)

        val button = findViewById<Button>(R.id.Add_quiz_button)
        val ev = findViewById<EditText>(R.id.csv)
        button.setOnClickListener{
            val csv = ev.text.toString()
            val datareader = CSVReader(StringReader(csv))

            var entries = datareader.readAll()
            entries = entries.drop(1)

            var map : MutableList<Map<String,String>>
            map = MutableList(0){mapOf("" to "") }

            var counter = 0

            val database =  FirebaseDatabase.getInstance("https://quizzler-1f7fa-default-rtdb.asia-southeast1.firebasedatabase.app").reference
            for (i in entries){

                database.child("Quizzes").child("Quiz1").child(counter.toString()).setValue(
                    mapOf("Question" to i[0],"Answer" to i[1]))
                counter++
            }
            println(map)

//            database.child("Quizzes").child("Quiz1").setValue(map).addOnSuccessListener {
//                Toast.makeText(this,"Uploaded",Toast.LENGTH_SHORT).show()
//
//            }



        }

    }


}