package personal.ivan.migo_quiz.feature.quiz2.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import personal.ivan.migo_quiz.R
import personal.ivan.migo_quiz.databinding.FragmentQuiz2DetailsBinding
import personal.ivan.migo_quiz.feature.quiz2.model.Quiz2ListVhBindingModel
import personal.ivan.migo_quiz.io.model.Quiz2Info

class Quiz2DetailsFragment : Fragment() {

    // View Binding
    private lateinit var binding: FragmentQuiz2DetailsBinding

    // Safe Argument
    private val args by navArgs<Quiz2DetailsFragmentArgs>()

    // region Life Cycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuiz2DetailsBinding.inflate(
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
        bind(model = args.selectedModel)
    }

    // endregion

    // region Fill Information on UI

    @SuppressLint("SetTextI18n")
    private fun bind(model: Quiz2ListVhBindingModel) {
        binding.apply {
            textViewPass.text = model.pass
            val type = root.context.getString(
                if (model.type == Quiz2Info.QUIZ_2_TYPE_HOUR) R.string.button_text_hour
                else R.string.button_text_day
            )
            textViewDescription.text = "${model.value} $type"
            textViewStatus.setText(model.status)
            textViewActivateDateTime.text =
                if (model.activateDateTime.isEmpty()) "N/A"
                else model.activateDateTime
            textViewDueDateTime.text =
                if (model.dueDateTime.isEmpty()) "N/A"
                else model.dueDateTime
        }
    }

    // endregion
}