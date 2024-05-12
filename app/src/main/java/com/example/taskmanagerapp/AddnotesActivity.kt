package com.example.taskmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.app.DatePickerDialog
import android.widget.Toast
import com.example.taskmanagerapp.databinding.ActivityAddnotesBinding
import java.util.*

class AddnotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddnotesBinding
    private lateinit var db: PaymentPlanDatabaseHelper
    private lateinit var setDate: EditText
    private lateinit var calendar: Calendar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddnotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = PaymentPlanDatabaseHelper(this)

        binding.PlannedButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val date = setDate.text.toString()
            val content = binding.AddDietDescription.text.toString()
            val paymentPlans = PaymentPlans(0, title, date, content)
            val isSuccess = db.insertNote(paymentPlans)
            if (isSuccess) {
                Toast.makeText(this, "Note Saved Successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to Save Note", Toast.LENGTH_SHORT).show()
            }
        }

        setDate = binding.Setdate
        setDate.setOnClickListener { showDatePickerDialog() }

        binding.DateSetButton.setOnClickListener {
            showDatePickerDialog()
        }


        calendar = Calendar.getInstance()
    }

    private fun showDatePickerDialog() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                setDate.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

}