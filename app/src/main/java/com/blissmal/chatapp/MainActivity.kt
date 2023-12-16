package com.blissmal.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

     lateinit var dir_loginpage: Button
     lateinit var dir_signuppage: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dir_loginpage = findViewById(R.id.btn_loginpage)
        dir_signuppage = findViewById(R.id.btn_signuppage)

        dir_loginpage.setOnClickListener {
            val intent = Intent(Intent(this, LoginActivity::class.java))
            startActivity(intent)
        }

        dir_signuppage.setOnClickListener {
            val intent = Intent(Intent(this, SignupActivity::class.java))
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null){
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}