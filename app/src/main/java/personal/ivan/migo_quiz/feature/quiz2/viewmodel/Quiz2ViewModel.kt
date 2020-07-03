package personal.ivan.migo_quiz.feature.quiz2.viewmodel

import android.view.View
import androidx.annotation.IdRes
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import personal.ivan.migo_quiz.feature.quiz2.interactor.Quiz2Interactor
import personal.ivan.migo_quiz.feature.quiz2.model.Quiz2ListVhBindingModel
import personal.ivan.migo_quiz.feature.quiz2.repository.Quiz2Repository
import personal.ivan.migo_quiz.feature.quiz2.routing.Quiz2Routing
import personal.ivan.migo_quiz.util.UiUtil

class Quiz2ViewModel @ViewModelInject constructor(
    private val repository: Quiz2Repository,
    private val interactor: Quiz2Interactor,
    private val routing: Quiz2Routing
) : ViewModel() {

    // region Quiz 2 list

    /**
     * Get quiz 2 list
     */
    fun getQuiz2List(): LiveData<List<Quiz2ListVhBindingModel>> =
        liveData { emit(value = repository.getQuiz2List()) }

    /**
     * Navigate to add page
     */
    fun navigateToAdd(view: View) {
        if (UiUtil.allowClick()) {
            routing.navigateToAdd(view = view)
        }
    }

    /**
     * Navigate to details page
     */
    fun navigateToDetails(
        view: View,
        model: Quiz2ListVhBindingModel
    ) {
        if (UiUtil.allowClick()) {
            routing.navigateToDetails(view = view, model = model)
        }
    }

    /**
     * Activate a quiz 2 item
     */
    fun activateQuiz2Item(model: Quiz2ListVhBindingModel): LiveData<List<Quiz2ListVhBindingModel>> =
        liveData {
            val ioModel = interactor.updateQuiz2IoModel(model = model)
            repository.updateQuiz2(model = ioModel)
            emit(value = repository.getQuiz2List())
        }

    // endregion

    // region Quiz 2 add

    /**
     * Add quiz 2 item
     */
    fun addQuiz2(
        value: String,
        @IdRes toggledButtonId: Int,
        view: View
    ) {
        if (UiUtil.allowClick()) {
            val model =
                interactor.createQuiz2IoModel(value = value, toggledButtonId = toggledButtonId)
            viewModelScope.launch {
                repository.insetQuiz2(model = model)
                routing.navigateBackToList(view = view)
            }
        }
    }

    // endregion
}