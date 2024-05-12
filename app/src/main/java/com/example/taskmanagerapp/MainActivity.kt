package com.example.taskmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taskmanagerapp.databinding.ActivityMainBinding
import com.example.taskmanagerapp.databinding.ActivityAddBillImageBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addDietplannerbutton.setOnClickListener {
            val intent = Intent(this, storedDietnotes::class.java)
            startActivity(intent)
        }

        binding.addBillImageButton.setOnClickListener {
            val intent = Intent(this, storedBill::class.java)
            startActivity(intent)
        }
    }
}
