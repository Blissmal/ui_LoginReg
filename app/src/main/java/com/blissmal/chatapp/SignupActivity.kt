package com.blissmal.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val signupname: EditText = findViewById(R.id.Edt_usrname)
        val signupemail: EditText = findViewById(R.id.Edt_emailaddr)
        val signupassword: EditText = findViewById(R.id.Edt_pass)
        val signupconfirmpass: EditText = findViewById(R.id.Edt_confirmpass)
        val signupbtn: Button = findViewById(R.id.Btn_signup)


        signupbtn.setOnClickListener {
            val name = signupname.text.toString()
            val email = signupemail.text.toString()
            val password = signupassword.text.toString()
            val cpassword = signupconfirmpass.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty()){
                if (name.isEmpty()){
                    signupname.error = "Enter your username"
                }
                if (email.isEmpty()){
                    signupemail.error = "Enter your email address"
                }
                if (password.isEmpty()){
                    signupassword.error = "Enter your password"
                }
                if (cpassword.isEmpty()){
                    signupconfirmpass.error = "reenter your password"
                }
            }else if (password != cpassword){
                signupconfirmpass.error = "Password does not match"
            }else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        val databaseRef = database.reference.child("users").child(auth.currentUser!!.uid)
                        val users: Users = Users(name, email, auth.currentUser!!.uid)
                        databaseRef.setValue(users).addOnCompleteListener {
                            if (it.isSuccessful){
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(this, "Something went wrong, try again", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else{
                        Toast.makeText(this, "Error while creating user try again", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }


    }
}