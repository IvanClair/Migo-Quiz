package personal.ivan.migo_quiz.feature.quiz2.routing

import android.view.View
import androidx.navigation.findNavController
import personal.ivan.migo_quiz.feature.quiz2.model.Quiz2ListVhBindingModel
import personal.ivan.migo_quiz.feature.quiz2.view.list.Quiz2ListFragmentDirections
import javax.inject.Inject

class Quiz2Routing @Inject constructor() {

    /**
     * Navigate to add quiz 2 item
     */
    fun navigateToAdd(view: View) {
        view.findNavController()
            .navigate(Quiz2ListFragmentDirections.actionQuiz2ListFragmentToQuiz2AddFragment())
    }

    /**
     * Navigate to quiz 2 item details
     */
    fun navigateToDetails(
        view: View,
        model: Quiz2ListVhBindingModel
    ) {
        view.findNavController()
            .navigate(
                Quiz2ListFragmentDirections.actionQuiz2ListFragmentToQuiz2DetailsFragment(
                    selectedModel = model
                )
            )
    }

    /**
     * Navigate back to quiz 2 list
     */
    fun navigateBackToList(view: View) {
        view.findNavController().navigateUp()
    }
}