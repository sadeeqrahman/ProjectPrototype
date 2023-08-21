package com.sadeeq.app.projectprototype.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sadeeq.app.projectprototype.R
import com.sadeeq.app.projectprototype.models.DateInfo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)

        generateDatesAndDays(2023)
        Log.e("CALENDARvIEW", generateDatesAndDays(2023).toString())
    }
    private fun generateDatesAndDays(year: Int): ArrayList<DateInfo> {
        val dateInfoList = ArrayList<DateInfo>()

        val dayAbbreviationFormat = SimpleDateFormat("E", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())

        val calendar = Calendar.getInstance()
        calendar.set(year, Calendar.JANUARY, 1) // Set the calendar to the beginning of the year

        while (calendar.get(Calendar.YEAR) == year) {
            val dayAbbreviation = dayAbbreviationFormat.format(calendar.time)
            val monthName = monthFormat.format(calendar.time)
            val day = calendar.get(Calendar.DAY_OF_MONTH).toString()
            val date = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH) + 1 // Months are zero-based

            val dateInfo = DateInfo(monthName, dayAbbreviation, day, date, month, year)
            dateInfoList.add(dateInfo)

            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return dateInfoList
    }



}