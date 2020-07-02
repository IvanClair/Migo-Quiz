package personal.ivan.migo_quiz.feature.quiz2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import personal.ivan.migo_quiz.databinding.FragmentQuiz2AddBinding

class Quiz2AddFragment : Fragment() {

    // View Binding
    private lateinit var binding: FragmentQuiz2AddBinding

    // region Life Cycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuiz2AddBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    // endregion
}