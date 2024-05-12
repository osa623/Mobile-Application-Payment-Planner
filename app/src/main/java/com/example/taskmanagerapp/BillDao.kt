import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BillDao {
    @Insert
    suspend fun insert(bill: Bill)

    @Query("SELECT * FROM bill_table")
    fun getAllBills(): LiveData<List<Bill>>
}
