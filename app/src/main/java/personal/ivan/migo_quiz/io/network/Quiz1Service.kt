package personal.ivan.migo_quiz.io.network

import personal.ivan.migo_quiz.io.model.Quiz1IoModel
import retrofit2.http.GET

interface Quiz1Service {

    @GET("/status")
    suspend fun getQuiz1IoModel(): Quiz1IoModel
}