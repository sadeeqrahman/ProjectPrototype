package com.sadeeq.app.projectprototype.activities.customCalendar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.sadeeq.app.projectprototype.R
import com.sadeeq.app.projectprototype.models.DateInfo

class DateInfoAdapter(
    context: Context,
    private val resource: Int,
    private val data: List<DateInfo>
) :
    ArrayAdapter<DateInfo>(context, resource, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(resource, parent, false)
        }

        val dateInfo = getItem(position)

        val dateTextView = itemView!!.findViewById<TextView>(R.id.dateTextView)
        dateTextView.text = dateInfo?.date.toString()

        val dayNameTextView = itemView.findViewById<TextView>(R.id.dayNameTextView)
        dayNameTextView.text = dateInfo?.dayName

//        val monthYearTextView = itemView.findViewById<TextView>(R.id.monthYearTextView)
//        monthYearTextView.text = "${dateInfo?.monthName} ${dateInfo?.year}"

        return itemView
    }
}
