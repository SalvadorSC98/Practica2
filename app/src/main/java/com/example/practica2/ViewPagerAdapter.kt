import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.practica2.CombinedData
import com.example.practica2.DataClasses

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val dataClasses: DataClasses) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return dataClasses.people.size
    }

    override fun createFragment(position: Int): Fragment {
        val combinedData = CombinedData(
            listOf(dataClasses.people[position]),
            listOf(dataClasses.students[position]),
            listOf(dataClasses.tutors[position])
        )
        return FragmentInfo.newInstance(combinedData)
    }
}
