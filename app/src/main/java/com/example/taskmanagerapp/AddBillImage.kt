package com.example.taskmanagerapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddBillImage : AppCompatActivity() {

    private lateinit var imagePickerButton: ImageView
    private lateinit var imagePreviewer: ImageView
    private lateinit var titleBillText: EditText
    private lateinit var setBilldate: EditText
    private lateinit var saveButton: Button

    companion object {
        const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bill_image)

        imagePickerButton = findViewById(R.id.ImagePickerButton)
        imagePreviewer = findViewById(R.id.imagePreviewer)
        titleBillText = findViewById(R.id.titleBillText)
        setBilldate = findViewById(R.id.SetBilldate)
        saveButton = findViewById(R.id.PlannedButton)

        imagePickerButton.setOnClickListener {
            openGallery()
        }

        saveButton.setOnClickListener {
            saveBill()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun saveBill() {
        // Perform your saving operation here using Room or any other database method
        // For demonstration purpose, I'm just showing a toast message
        Toast.makeText(this, "Bill saved successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage = data.data
            imagePreviewer.setImageURI(selectedImage)
        }
    }
}
