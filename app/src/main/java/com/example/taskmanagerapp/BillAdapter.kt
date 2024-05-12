import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskmanagerapp.R

class BillAdapter(private val context: Context) : RecyclerView.Adapter<BillAdapter.ViewHolder>() {

    private var billList: List<Bill> = emptyList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleBillView: TextView = itemView.findViewById(R.id.titleBillView)
        val dateBillView: TextView = itemView.findViewById(R.id.dateBillView)
        val billPrew: ImageView = itemView.findViewById(R.id.billPrew)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bill_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = billList[position]
        holder.titleBillView.text = currentItem.title
        holder.dateBillView.text = currentItem.date
        Glide.with(holder.itemView.context).load(currentItem.imagePath).into(holder.billPrew)

        holder.itemView.setOnClickListener {
            // Handle item click if needed
        }
    }

    override fun getItemCount() = billList.size

    fun setBillList(bills: List<Bill>) {
        this.billList = bills
        notifyDataSetChanged()
    }




}
