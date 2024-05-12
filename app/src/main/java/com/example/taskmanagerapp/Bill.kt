import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill_table")
data class Bill(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date: String,
    val imagePath: String // Path to the image file
)
