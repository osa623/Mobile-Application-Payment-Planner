package com.example.taskmanagerapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PlansAdapter(private var paymentPlans: List<PaymentPlans>, context: Context) : RecyclerView.Adapter<PlansAdapter.PlanViewHolder>() {

    private val db: PaymentPlanDatabaseHelper = PaymentPlanDatabaseHelper(context)

    class PlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.titleView)
        val dateView: TextView = itemView.findViewById(R.id.dateView)
        val planView: TextView = itemView.findViewById(R.id.planView)
        val updatePlanButton: ImageView = itemView.findViewById(R.id.updatePlanButton)
        val deletePlanButton: ImageView = itemView.findViewById(R.id.deletePlanButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plan_item, parent, false)
        return PlanViewHolder(view)
    }

    override fun getItemCount(): Int = paymentPlans.size

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val paymentPlan = paymentPlans[position]
        holder.titleView.text = paymentPlan.title
        holder.dateView.text = paymentPlan.date
        holder.planView.text = paymentPlan.content

        holder.updatePlanButton.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdatePlans::class.java).apply {
                putExtra("plan_id", paymentPlan.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deletePlanButton.setOnClickListener{
            db.deletePlan(paymentPlan.id)
            refreshPlans(db.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note has been deleted", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshPlans(newPlans: List<PaymentPlans>) {
        paymentPlans = newPlans
        notifyDataSetChanged()
    }
}
