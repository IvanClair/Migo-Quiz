package personal.ivan.migo_quiz.feature.quiz1.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import personal.ivan.migo_quiz.feature.quiz1.repository.Quiz1Repository
import personal.ivan.migo_quiz.io.model.Quiz1IoModel

class Quiz1ViewModel @ViewModelInject constructor(private val repository: Quiz1Repository) :
    ViewModel() {

    /**
     * Get quiz 1 result
     */
    fun getQuiz1Result(): LiveData<Quiz1IoModel> = liveData(Dispatchers.IO) {
        try {
            emit(value = repository.getQuiz1IoModel())
        } catch (e: Exception) {
            Log.e(Quiz1ViewModel::class.java.simpleName, e.toString())
        }
    }
}