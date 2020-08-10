package com.example.lab8

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "MAIN"
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        tv_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        tv_reset_password.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
        btn_login.setOnClickListener {
            val email = ed_email.text.toString()
            val password = ed_password.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        // ...
                    }

                    // ...
                }

        }
    }
}