package com.example.lab8

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        tv_back.setOnClickListener {
            finish()
        }
        btn_reset.setOnClickListener {
            val email = ed_email_reset.text.toString()
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Thành Công", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Không Thành Công", Toast.LENGTH_LONG).show()
                    }
                }
        }

    }
}