package personal.ivan.migo_quiz.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import personal.ivan.migo_quiz.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    // View Binding
    private lateinit var binding: FragmentHomeBinding

    // region Life Cycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // quiz 1
        binding.buttonQuiz1.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQuiz1Fragment())
        }

        // quiz 2
        binding.buttonQuiz2.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQuiz2ListFragment())
        }
    }

    // endregion
}