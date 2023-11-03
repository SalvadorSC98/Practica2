import java.time.LocalDate

sealed class CalendarItem {
    data class Month(val name: String) : CalendarItem()
    data class Day(val dayOfMonth: LocalDate, val dayOfWeek: Int, var state: String = "Formación") : CalendarItem()
}

