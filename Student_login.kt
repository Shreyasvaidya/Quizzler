package com.example.quizzler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Student_login : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContentView(R.layout.activity_student_login)
        val gsibutton = findViewById<SignInButton>(R.id.student_g_sign_in_button)
        gsibutton.setSize(SignInButton.SIZE_WIDE)
        val Login_btn = findViewById<Button>(R.id.stud_login_button)
        val emailview = findViewById<EditText>(R.id.stud_login_email)
        val psswd_view = findViewById<EditText>(R.id.stud_login_psswd)
        Login_btn.setOnClickListener{
            val email = emailview.text.toString()
            val psswrd = psswd_view.text.toString()
            println(psswrd)
            auth.signInWithEmailAndPassword(email,psswrd).addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    val uid = auth.currentUser?.uid

                    val database =  FirebaseDatabase.getInstance("https://quizzler-1f7fa-default-rtdb.asia-southeast1.firebasedatabase.app").reference
                    val query = database.child("Users").child(uid!!).child("role")

                    query.get().addOnSuccessListener{
                        if(it.value.toString()=="Teacher"){
                            intent = Intent(this,Teacher_home::class.java)
                            startActivity(intent)
                        }
                        else{
                            intent = Intent(this,Student_home::class.java)
                            startActivity(intent)
                        }







                    }.addOnFailureListener{
                        println(it)
                    }

                }

            }


        }
        gsibutton.setOnClickListener{
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        }

    }
}