import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practica2.AttendanceActivity
import com.example.practica2.CombinedData
import com.example.practica2.Person
import com.example.practica2.Student
import com.example.practica2.Tutor
import com.example.practica2.databinding.FragmentInfoBinding

class FragmentInfo : Fragment() {
    private lateinit var binding: FragmentInfoBinding

    companion object {
        fun newInstance(combinedData: CombinedData): FragmentInfo {
            val fragment = FragmentInfo()
            val args = Bundle()
            args.putSerializable("combinedData", combinedData)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        val combinedData = arguments?.getSerializable("combinedData") as CombinedData
        val position = arguments?.getInt("position") ?: 0

        personInfo(combinedData.people[position])
        studentInfo(combinedData.students[position])
        tutorInfo(combinedData.tutors[position])

        binding.calendarImageView.setOnClickListener {
            val intent = Intent(requireContext(), AttendanceActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }

    private fun personInfo(person: Person) {
        binding.nameTextView.text = person.name
        binding.surnameTextView.text = person.surname
        binding.emailTextView.text = person.email
    }

    private fun studentInfo(student: Student) {
        binding.cityTextView.text = student.city
        binding.centerTextView.text = student.center
        binding.studentImageView.setImageResource(student.photo)
    }

    private fun tutorInfo(tutor: Tutor) {
        binding.tutorNameTextView.text = tutor.tutorName
        binding.tutorSurnameTextView.text = tutor.tutorSurname
    }
}
