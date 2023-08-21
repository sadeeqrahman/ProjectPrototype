package com.sadeeq.app.projectprototype.activities.adapters

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView
import com.sadeeq.app.projectprototype.R
import com.sadeeq.app.projectprototype.models.DateInfo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CustomCalendarView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private lateinit var gridView: GridView
    private lateinit var prevMonthButton: Button
    private lateinit var nextMonthButton: Button

    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("d", Locale.getDefault())
    private val monthFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_calendar_view, this, true)
        orientation = VERTICAL

        gridView = findViewById(R.id.calendarGridView)
        prevMonthButton = findViewById(R.id.prevMonthButton)
        nextMonthButton = findViewById(R.id.nextMonthButton)

        setupCalendar()

        prevMonthButton.setOnClickListener { showPreviousMonth() }
        nextMonthButton.setOnClickListener { showNextMonth() }
    }

    // ...

    private fun setupCalendar() {
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentYear = calendar.get(Calendar.YEAR)
        val dateInfoList = generateDatesAndDays(currentYear, currentMonth)

        val adapter = DateInfoAdapter(context, R.layout.calendar_item, dateInfoList)
        gridView.adapter = adapter

        // Update the title to display the current month and year
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        titleTextView.text = monthFormat.format(calendar.time)
    }

// ...


    private fun generateDatesAndDays(year: Int, month: Int): List<DateInfo> {
        val dateInfoList = mutableListOf<DateInfo>()

        val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())

        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1) // Set the calendar to the beginning of the specified month

        val currentMonth = calendar.get(Calendar.MONTH)
        while (calendar.get(Calendar.MONTH) == currentMonth) {
            val dayName = dayFormat.format(calendar.time)
            val monthName = monthFormat.format(calendar.time)
            val day = calendar.get(Calendar.DAY_OF_MONTH).toString()
            val date = calendar.get(Calendar.DAY_OF_MONTH)

            val dateInfo = DateInfo(monthName, dayName, day, date, currentMonth + 1, year)
            dateInfoList.add(dateInfo)

            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return dateInfoList
    }


    private fun showPreviousMonth() {
        calendar.add(Calendar.MONTH, -1)
        setupCalendar()
    }

    private fun showNextMonth() {
        calendar.add(Calendar.MONTH, 1)
        setupCalendar()
    }
}