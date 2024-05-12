package com.example.taskmanagerapp

import BillAdapter
import BillViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanagerapp.databinding.ActivityStoredBillBinding

class storedBill : AppCompatActivity() {

    private lateinit var binding: ActivityStoredBillBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoredBillBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.addBillsButton.setOnClickListener{

            val intent = Intent(this, AddBillImage::class.java)
            startActivity(intent)
        }
    }
}