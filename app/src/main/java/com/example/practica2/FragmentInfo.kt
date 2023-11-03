import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practica2.AttendanceActivity
import com.example.practica2.DataClasses.students
import com.example.practica2.data.Student
import com.example.practica2.data.Tutor
import com.example.practica2.databinding.FragmentInfoBinding

class FragmentInfo : Fragment() {
    private lateinit var binding: FragmentInfoBinding

    companion object {
        fun newInstance(studentList: List<Student>, tutorList: List<Tutor>, position: Int): FragmentInfo {
            val fragment = FragmentInfo()
            val args = Bundle()
            args.putInt("position", position)
            args.putSerializable("studentList", ArrayList(studentList))
            args.putSerializable("tutorList", ArrayList(tutorList))
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
        val position = arguments?.getInt("position") ?: 0

        setUpStudentViews(students[position])

        binding.calendarImageView.setOnClickListener {
            val intent = Intent(requireContext(), AttendanceActivity::class.java)
            intent.putExtra("prueba", students[position])
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }

    private fun setUpStudentViews(student: Student) {
        binding.nameTextView.text = student.name
        binding.cityTextView.text = student.city
        binding.surnameTextView.text = student.surname
        binding.emailTextView.text = student.email
        binding.centerTextView.text = student.center
        binding.studentImageView.setImageResource(student.photo)
        binding.tutorNameTextView.text = student.tutor.name
        binding.tutorSurnameTextView.text = student.tutor.surname
    }
}
