package personal.ivan.migo_quiz.feature.quiz1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import personal.ivan.migo_quiz.R
import personal.ivan.migo_quiz.databinding.FragmentQuiz1Binding
import personal.ivan.migo_quiz.feature.quiz1.viewmodel.Quiz1ViewModel
import personal.ivan.migo_quiz.util.showOrHide

@AndroidEntryPoint
class Quiz1Fragment : Fragment() {

    // View Binding
    private lateinit var binding: FragmentQuiz1Binding

    // View Model
    private val viewModel by navGraphViewModels<Quiz1ViewModel>(R.id.nav_graph_main) { defaultViewModelProviderFactory }

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

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        getQuiz1Result()
    }

    // endregion

    // region Observe LiveData

    private fun getQuiz1Result() {
        viewModel.getQuiz1Result().observe(
            viewLifecycleOwner,
            Observer {
                binding.apply {
                    progressBar showOrHide false
                    textViewResult.text = it.toString()
                }
            }
        )
    }

    // endregion
}