package com.example.taskmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanagerapp.databinding.ActivityStoredDietnotesBinding

class storedPlans : AppCompatActivity() {

    private lateinit var binding: ActivityStoredDietnotesBinding
    private lateinit var db: PaymentPlanDatabaseHelper
    private lateinit var plansAdapter: PlansAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoredDietnotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = PaymentPlanDatabaseHelper(this)
        plansAdapter = PlansAdapter(db.getAllNotes(), this)

        // Set StaggeredGridLayoutManager with 2 columns and vertical orientation
        binding.plansRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.plansRecyclerView.adapter = plansAdapter

        binding.addDietbutton.setOnClickListener {
            val intent = Intent(this, AddnotesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        plansAdapter.refreshPlans(db.getAllNotes())
    }
}
