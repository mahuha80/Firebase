package com.example.lab8

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.ed_email
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    val TAG = "RegisterActivity"
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        btn_register.setOnClickListener {
            val email = ed_email.text.toString()
            val password = ed_password_register.text.toString()
            if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ các trường ", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        // ...
                    }
            }
        }
    }
}