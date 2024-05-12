import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Bill::class], version = 1, exportSchema = false)
abstract class BillDatabase : RoomDatabase() {
    abstract fun billDao(): BillDao
}
