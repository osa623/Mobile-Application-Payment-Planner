import androidx.lifecycle.LiveData

class BillRepository(private val billDao: BillDao) {
    val allBills: LiveData<List<Bill>> = billDao.getAllBills()

    suspend fun insert(bill: Bill) {
        billDao.insert(bill)
    }
}
