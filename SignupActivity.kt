package com.example.quizzler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class SignupActivity : AppCompatActivity() {
    private lateinit var  auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        val min_psswd_len = 6

        val usernameEditText = findViewById<EditText>(R.id.signup_name)
        val passwordEditText = findViewById<EditText>(R.id.signup_passwd)
        val user_emailEditText = findViewById<EditText>(R.id.signup_email)
        val stud_or_teacherRadioGroup = findViewById<RadioGroup>(R.id.StudOrTeacher)
        val submitButton = findViewById<Button>(R.id.register_button)
        submitButton.setOnClickListener(View.OnClickListener {
            val username = usernameEditText.text.toString()
            val psswd = passwordEditText.text.toString()
            val email = user_emailEditText.text.toString()

            val stud_or_teacher = if (stud_or_teacherRadioGroup.checkedRadioButtonId==R.id.Teach) "Teacher" else "Student"


            if (username.isEmpty() || email.isEmpty() || psswd.isEmpty() ) {
                Toast.makeText(this, "Please fill all the fields.", Toast.LENGTH_SHORT).show()
            }
            else if(psswd.length<min_psswd_len){
                Toast.makeText(this, "Password should have atleast $min_psswd_len characters.", Toast.LENGTH_SHORT).show()

            }
            else {
               auth.createUserWithEmailAndPassword(email,psswd).addOnCompleteListener(this) {task ->
                   if(task.isSuccessful){
                       val user = auth.currentUser
                       val newuserdetails = User(id = user!!.uid,name =username, password = psswd, email = email,role = stud_or_teacher )
                       val database =  FirebaseDatabase.getInstance("https://quizzler-1f7fa-default-rtdb.asia-southeast1.firebasedatabase.app").reference
                        database.child("Users").child(user!!.uid).setValue(newuserdetails).addOnCompleteListener(this){task->
                            if (task.isSuccessful){
                                Toast.makeText(this, "User created successfully!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()

                            }
                            else{
                                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()


                            }
                        }
                   }

               }

            }
        }
        )

    }

}