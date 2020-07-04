@file:Suppress("BlockingMethodInNonBlockingContext")

package personal.ivan.migo_quiz.feature.quiz1.repository

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import personal.ivan.migo_quiz.BuildConfig
import personal.ivan.migo_quiz.R
import personal.ivan.migo_quiz.io.model.Quiz1IoModel
import personal.ivan.migo_quiz.io.network.Quiz1Service
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.InetAddress
import java.net.URL
import javax.inject.Inject

class Quiz1Repository @Inject constructor(private val context: Context) {

    private var service: Quiz1Service? = null

    /**
     * Get [Quiz1IoModel] result
     */
    suspend fun getQuiz1IoModel(): Quiz1IoModel {
        if (service == null) {
            initialService()
        }
        return service!!.getQuiz1IoModel()
    }

    /**
     * Initial [Quiz1Service]
     */
    private fun initialService() {
        val url = getBaseUrlAddress()
        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .apply {
                        // log on debug mode
                        if (BuildConfig.DEBUG) {
                            addInterceptor(HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            })
                        }
                    }
                    .build())
            .build()
            .create(Quiz1Service::class.java)
    }

    /**
     * Get URL by condition
     */
    private fun getBaseUrlAddress(): String {
        val privateUrl = context.getString(R.string.base_url_private)
        val iNet = InetAddress.getByName(URL(privateUrl).host)
        val reachable = iNet.isReachable(5000)
        return if (reachable) privateUrl else context.getString(R.string.base_url_public)
    }
}