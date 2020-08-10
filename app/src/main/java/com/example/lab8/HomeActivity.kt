package com.example.lab8

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    val TAG = "HomeActivity"
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        auth = FirebaseAuth.getInstance()
        btn_change_email.setOnClickListener {
            val email = ed_email_home.text.toString()
            FirebaseAuth.getInstance().currentUser?.updateEmail(email)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "User email đã được update", Toast.LENGTH_LONG).show()
                        Log.d(TAG, "User email address updated.")
                    } else {
                        Toast.makeText(this, "Update user email thất bại", Toast.LENGTH_LONG).show()
                    }
                }

        }
        btn_change_password.setOnClickListener {
            val password = ed_email_home.text.toString()
            FirebaseAuth.getInstance().currentUser?.updatePassword(password)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "update password thành công", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "update password thất bại", Toast.LENGTH_LONG).show()
                    }
                }

        }

        btn_set_password_reset_email.setOnClickListener {
            val emailAddress = ed_email_home.text.toString()
            auth.sendPasswordResetEmail(emailAddress).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email đã được gửi", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Thất bại", Toast.LENGTH_LONG).show()
                }
            }

        }

        btn_remove_user.setOnClickListener {
            FirebaseAuth.getInstance().currentUser?.delete()
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User account deleted.")
                        Toast.makeText(this, "Đã xóa tài khoản", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Thất bại", Toast.LENGTH_LONG).show()
                    }
                }
        }
        btn_sign_out.setOnClickListener {
            auth.signOut()
            finish()
        }
    }
}