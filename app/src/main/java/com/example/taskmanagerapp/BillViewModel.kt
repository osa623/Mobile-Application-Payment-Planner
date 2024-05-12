import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData

class BillViewModel(private val repository: BillRepository) : ViewModel() {
    val allBills: LiveData<List<Bill>> = repository.allBills

    fun insert(bill: Bill) = viewModelScope.launch {
        repository.insert(bill)
    }
}
