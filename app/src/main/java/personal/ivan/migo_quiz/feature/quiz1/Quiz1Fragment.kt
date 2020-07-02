package personal.ivan.migo_quiz.feature.quiz1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import personal.ivan.migo_quiz.databinding.FragmentQuiz1Binding

class Quiz1Fragment : Fragment() {

    // View Binding
    private lateinit var binding: FragmentQuiz1Binding

    // region Life Cycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuiz1Binding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    // endregion
}