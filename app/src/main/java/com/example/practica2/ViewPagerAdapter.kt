import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.practica2.DataClasses

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val dataClasses: DataClasses) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return dataClasses.students.size
    }

    override fun createFragment(position: Int): Fragment {
        val studentList = dataClasses.students
        val tutorList = dataClasses.tutors
        return FragmentInfo.newInstance(studentList, tutorList, position)
    }

}
