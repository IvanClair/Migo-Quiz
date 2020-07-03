package personal.ivan.migo_quiz.feature.quiz2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import personal.ivan.migo_quiz.R
import personal.ivan.migo_quiz.databinding.FragmentQuiz2AddBinding
import personal.ivan.migo_quiz.feature.quiz2.viewmodel.Quiz2ViewModel
import personal.ivan.migo_quiz.util.showOrHide

class Quiz2AddFragment : Fragment() {

    // View Binding
    private lateinit var binding: FragmentQuiz2AddBinding

    // View Model
    private val viewModel by navGraphViewModels<Quiz2ViewModel>(R.id.nav_graph_main) { defaultViewModelProviderFactory }

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

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initialToggleGroup()
        initialRadioGroups()
        initialAddButton()
    }

    // endregion

    // region Toggle Group

    private fun initialToggleGroup() {
        binding.apply {
            groupType.addOnButtonCheckedListener { _, checkedId, isChecked ->
                groupDay showOrHide (checkedId == R.id.button_day && isChecked)
                groupHour showOrHide (checkedId == R.id.button_hour && isChecked)
                val radioGroup = if (groupDay.visibility == View.VISIBLE) groupDay else groupHour
                val checkedRadioButton =
                    radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                updateValue(number = checkedRadioButton.text.toString())
            }
        }
    }

    // endregion

    // region Radio Group

    private fun initialRadioGroups() {
        fun setOnCheckedListener(group: RadioGroup) {
            group.setOnCheckedChangeListener { radioGroup, i ->
                updateValue(number = radioGroup.findViewById<RadioButton>(i).text.toString())
            }
        }
        binding.apply {
            setOnCheckedListener(group = groupDay)
            setOnCheckedListener(group = groupHour)
        }
    }

    // endregion

    // region TextView

    private fun updateValue(number: String) {
        binding.textViewValue.text = number
    }

    private fun getValue(): String = binding.textViewValue.text.toString()

    // endregion

    // region Add Button

    private fun initialAddButton() {
        binding.apply {
            buttonAdd.setOnClickListener {
                viewModel.addQuiz2(
                    value = getValue(),
                    toggledButtonId = groupType.checkedButtonId,
                    view = it
                )
            }
        }
    }

    // endregion
}