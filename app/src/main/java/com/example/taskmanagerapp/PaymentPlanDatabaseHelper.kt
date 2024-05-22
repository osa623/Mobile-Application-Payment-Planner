package com.example.taskmanagerapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PaymentPlanDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "paymentplandatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "PaymentNotes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_CONTENT = "content"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_DATE TEXT, $COLUMN_CONTENT TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertNote(paymentPlans: PaymentPlans): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, paymentPlans.title)
            put(COLUMN_DATE, paymentPlans.date)
            put(COLUMN_CONTENT, paymentPlans.content)
        }
        val isSuccess = db.insert(TABLE_NAME, null, values) != -1L
        db.close()
        return isSuccess
    }

    fun getAllNotes(): List<PaymentPlans> {
        val planList = mutableListOf<PaymentPlans>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            val paymentPlans = PaymentPlans(id, title, date, content)
            planList.add(paymentPlans)
        }
        cursor.close()
        db.close()
        return planList
    }

    fun updateNotes(paymentPlans: PaymentPlans) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, paymentPlans.title)
            put(COLUMN_DATE, paymentPlans.date)
            put(COLUMN_CONTENT, paymentPlans.content)
        }

        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(paymentPlans.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getPlanByID(paymentPlansId: Int): PaymentPlans {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $paymentPlansId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

        cursor.close()
        db.close()
        return PaymentPlans(id, title, date, content)
    }

    fun deletePlan(paymentPlansId: Int){

        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(paymentPlansId.toString())

        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }
}
