package com.example.taskmanagerapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.taskmanagerapp.databinding.ActivityUpdatePlansBinding
import java.util.Calendar

class UpdatePlans : AppCompatActivity() {

    private lateinit var binding: ActivityUpdatePlansBinding
    private lateinit var db: PaymentPlanDatabaseHelper
    private lateinit var setDate: EditText
    private lateinit var calendar: Calendar
    private var paymentPlanid: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePlansBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = PaymentPlanDatabaseHelper(this)

        paymentPlanid = intent.getIntExtra("plan_id", -1)
        if(paymentPlanid == -1){
            finish()
            return
        }

        val plan = db.getPlanByID(paymentPlanid)
        binding.updatetitleEditText.setText(plan.title)
        binding.updateSetdate.setText(plan.date)
        binding.updatePaymentcontent.setText(plan.content)

        binding.updatePlannedButton.setOnClickListener{

            val newTitle = binding.updatetitleEditText.text.toString()
            val newDate = binding.updateSetdate.text.toString()
            val newContent = binding.updatePaymentcontent.text.toString()
            val updatePlan = PaymentPlans(paymentPlanid, newTitle, newDate, newContent)
            db.updateNotes(updatePlan)
            finish()
            Toast.makeText(this,"Changes have been saved", Toast.LENGTH_SHORT).show()
        }

        // Initialize views and calendar
        setDate = binding.updateSetdate
        calendar = Calendar.getInstance()

        // Set click listener for date picker dialog
        setDate.setOnClickListener { showDatePickerDialog() }

        binding.updateDateSetButton.setOnClickListener {
            showDatePickerDialog()
        }
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
