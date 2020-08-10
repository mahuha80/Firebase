package com.example.lab8

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_upload_image.*
import java.io.ByteArrayOutputStream
import java.io.IOException

class UploadImageActivity : AppCompatActivity() {
    val PICK_IMAGE_REQUEST = 234
    val storage = Firebase.storage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)
        btn_choose.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(
                    intent, "Select Picture "
                ), PICK_IMAGE_REQUEST
            )
        }
        btn_upload.setOnClickListener {
            val storageRef = storage.reference
            val mountainsRef = storageRef.child("mountains.jpg")
            img_choosed.isDrawingCacheEnabled = true
            img_choosed.buildDrawingCache()
            val bitmap = (img_choosed.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            var uploadTask = mountainsRef.putBytes(data)
            uploadTask.addOnFailureListener {
                Toast.makeText(this, "failure", Toast.LENGTH_LONG).show()
            }.addOnSuccessListener {
                Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                img_choosed.setImageBitmap(bitmap)
            } catch (io: IOException) {
                io.printStackTrace()
            }
        }
    }
}