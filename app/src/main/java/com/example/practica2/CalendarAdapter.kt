import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica2.databinding.ItemDayBinding
import com.example.practica2.databinding.ItemMonthBinding
import com.google.android.material.snackbar.Snackbar
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter
import java.util.Locale

class CalendarAdapter(private val items: List<CalendarItem>, private val isTableMode: Boolean, private val rootView: View) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MONTH_VIEW_TYPE -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemMonthBinding.inflate(inflater, parent, false)
                MonthViewHolder(binding)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemDayBinding.inflate(inflater, parent, false)
                DayViewHolder(binding, binding.root, this@CalendarAdapter, isTableMode)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is MonthViewHolder -> holder.bind(item as CalendarItem.Month)
            is DayViewHolder -> holder.bind(item as CalendarItem.Day)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = items[position]) {
            is CalendarItem.Month -> MONTH_VIEW_TYPE
            is CalendarItem.Day -> DAY_VIEW_TYPE
            else -> error("Unknown item type: $item")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MonthViewHolder(private val binding: ItemMonthBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(month: CalendarItem.Month) {
            binding.textMonth.text = month.name
        }
    }

    class DayViewHolder(
        private val binding: ItemDayBinding,
        private val rootView: View,
        private val adapter: CalendarAdapter,
        private val isTableMode: Boolean
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(day: CalendarItem.Day) {
            val calendar = day.dayOfMonth
            val dayOfWeek = calendar.dayOfWeek
            val formatter = DateTimeFormatter.ofPattern("EEEE", Locale("es", "ES"))
            val dayOfWeekString = calendar.format(formatter)

            val textToShow = if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                ""
            } else {
                if (isTableMode) {
                    when (dayOfWeek) {
                        DayOfWeek.MONDAY -> "L${day.dayOfMonth.dayOfMonth}"
                        DayOfWeek.TUESDAY -> "M${day.dayOfMonth.dayOfMonth}"
                        DayOfWeek.WEDNESDAY -> "X${day.dayOfMonth.dayOfMonth}"
                        DayOfWeek.THURSDAY -> "J${day.dayOfMonth.dayOfMonth}"
                        DayOfWeek.FRIDAY -> "V${day.dayOfMonth.dayOfMonth}"
                        else -> ""
                    }
                } else {
                    "$dayOfWeekString ${day.dayOfMonth.dayOfMonth}: ${day.state}"
                }
            }

            when (day.state) {
                "Formación" -> {
                    binding.textDay.setTextColor(android.graphics.Color.BLACK)
                }
                "Vacaciones" -> {
                    binding.textDay.setTextColor(android.graphics.Color.GREEN)
                }
                "Centro" -> {
                    binding.textDay.setTextColor(android.graphics.Color.YELLOW)
                }
                else -> {
                    binding.textDay.setTextColor(android.graphics.Color.BLACK)
                }
            }

            binding.textDay.text = textToShow

            binding.root.setOnClickListener {
                showDateAlert(day)
            }

            binding.root.setOnLongClickListener {
                val previousState = day.state
                showChangeStateDialog(day, previousState, adapterPosition)
                true
            }
        }

        private fun showDateAlert(day: CalendarItem.Day) {
            val formatter = DateTimeFormatter.ofPattern("EEEE d 'de' MMMM 'de' yyyy", Locale("es", "ES"))
            val dateString = day.dayOfMonth.format(formatter)
            val message = "El $dateString estuvo de ${day.state}"

            AlertDialog.Builder(binding.root.context)
                .setTitle("Información de la fecha")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        }

        private fun showChangeStateDialog(day: CalendarItem.Day, previousState: String, position: Int) {
            AlertDialog.Builder(binding.root.context)
                .setTitle("Selecciona un estado")
                .setItems(arrayOf("Formación", "Vacaciones", "Centro")) { _, which ->
                    when (which) {
                        0 -> {
                            day.state = "Formación"
                            bind(day)
                        }
                        1 -> {
                            day.state = "Vacaciones"
                            bind(day)
                        }
                        2 -> {
                            day.state = "Centro"
                            bind(day)
                        }
                    }
                    showUndoSnackbar(previousState, position)
                }
                .show()
        }

        private fun showUndoSnackbar(previousState: String, position: Int) {
            Snackbar.make(adapter.rootView, "Cambios deshechos", Snackbar.LENGTH_LONG)
                .setAction("Deshacer") {
                    (adapter.items[position] as? CalendarItem.Day)?.state = previousState
                    adapter.notifyItemChanged(position)
                }
                .show()
        }
    }

    companion object {
        private const val MONTH_VIEW_TYPE = 0
        private const val DAY_VIEW_TYPE = 1
    }
}
