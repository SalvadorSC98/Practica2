package com.example.practica2

import CalendarAdapter
import CalendarItem
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica2.data.Student
import com.example.practica2.databinding.ActivityAttendanceBinding
import com.google.android.material.appbar.AppBarLayout
import java.time.DateTimeException
import java.time.LocalDate
import java.time.Month

class AttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAttendanceBinding
    private var isTableMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)
        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val backArrow = binding.backArrow
        val personImage = binding.personImage
        val nameLabel = binding.nameLabel
        val appBarLayout = binding.appBar
        val changeViewButton = binding.changeViewButton

        val student = intent.getSerializableExtra("prueba") as? Student
        val layoutParams = nameLabel.layoutParams as ViewGroup.MarginLayoutParams

        backArrow.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset == 0) {
                val fullName = "${student?.name} ${student?.surname}"
                nameLabel.text = fullName
                personImage.visibility = View.VISIBLE
                personImage.isClickable = true
                layoutParams.setMargins(0,0,0,280)
                nameLabel.layoutParams = layoutParams
            } else if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                nameLabel.text = "${student?.name}"
                personImage.visibility = View.INVISIBLE
                personImage.isClickable = false
                layoutParams.setMargins(0,0,0,180)
                nameLabel.layoutParams = layoutParams
            } else {
                // El CollapsingToolbarLayout está en un estado intermedio
                // Puedes realizar acciones específicas cuando está en un estado intermedio
            }
        })

        val months = listOf("Septiembre", "Octubre", "Noviembre", "Diciembre")
        val items = mutableListOf<CalendarItem>()
        val start = LocalDate.of(2023, Month.SEPTEMBER, 1)

        months.forEach { monthName ->
            items.add(CalendarItem.Month(monthName))

            val daysInMonth = when (monthName) {
                "Octubre", "Diciembre" -> 31
                "Septiembre", "Noviembre" -> 30
                else -> 28
            }

            val startOfMonth = start.plusMonths(months.indexOf(monthName).toLong())

            (1..daysInMonth).forEach { dayOfMonth ->
                try {
                    val date = startOfMonth.withDayOfMonth(dayOfMonth)
                    val dayOfWeek = date.dayOfWeek.value

                    if (dayOfWeek != 6 && dayOfWeek != 7) {
                        items.add(CalendarItem.Day(date, dayOfWeek))
                    }
                } catch (e: DateTimeException) {
                    e.printStackTrace()
                }
            }
        }

        val recyclerView = binding.recyclerView
        val calendarAdapter = CalendarAdapter(items, isTableMode, binding.root)

        changeViewButton.setOnClickListener {
            isTableMode = !isTableMode
            recyclerView.adapter = CalendarAdapter(items, isTableMode, binding.root)
            if (isTableMode) {
                changeViewButton.setImageResource(R.drawable.tablemode)
                val gridLayoutManager = GridLayoutManager(this, 5)
                recyclerView.layoutManager = gridLayoutManager
                gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (items[position] is CalendarItem.Month) 5 else 1
                    }
                }
            } else {
                changeViewButton.setImageResource(R.drawable.listmode)
                val linearLayoutManager = LinearLayoutManager(this)
                recyclerView.layoutManager = linearLayoutManager
            }

            calendarAdapter.notifyDataSetChanged()
        }
    }
}
