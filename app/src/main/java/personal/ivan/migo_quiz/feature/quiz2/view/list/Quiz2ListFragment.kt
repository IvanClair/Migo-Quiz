package personal.ivan.migo_quiz.feature.quiz2.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import personal.ivan.migo_quiz.R
import personal.ivan.migo_quiz.databinding.FragmentQuiz2ListBinding
import personal.ivan.migo_quiz.feature.quiz2.model.Quiz2ListVhBindingModel
import personal.ivan.migo_quiz.feature.quiz2.viewmodel.Quiz2ViewModel
import personal.ivan.migo_quiz.util.showOrHide

@AndroidEntryPoint
class Quiz2ListFragment : Fragment() {

    // View Binding
    private lateinit var binding: FragmentQuiz2ListBinding

    // View Model
    private val viewModel by navGraphViewModels<Quiz2ViewModel>(R.id.nav_graph_main) { defaultViewModelProviderFactory }

    // region Life Cycle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuiz2ListBinding.inflate(
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
        initialRecyclerView()
        initialFabButton()
        observeAllQuiz2List()
    }

    // endregion

    // region Observe LiveData

    private fun observeAllQuiz2List() {
        viewModel.getQuiz2List().observe(
            viewLifecycleOwner,
            Observer {
                hideLoading()
                updateDataList(dataList = it)
            }
        )
    }

    private fun observeUpdatedQuiz2List(model: Quiz2ListVhBindingModel) {
        viewModel.activateQuiz2Item(model = model).observe(
            viewLifecycleOwner,
            Observer {
                updateDataList(dataList = it)
            }
        )
    }

    // endregion

    // region RecyclerView

    private fun initialRecyclerView() {
        binding.recyclerQuiz2.apply {
            val quiz2Adapter = Quiz2Adapter()
            quiz2Adapter.setOnItemClickListener(listener = View.OnClickListener {
                viewModel.navigateToDetails(view = it, model = it.tag as Quiz2ListVhBindingModel)
            })
            quiz2Adapter.setOnActivateClickListener(listener = View.OnClickListener {
                observeUpdatedQuiz2List(model = it.tag as Quiz2ListVhBindingModel)
            })
            adapter = quiz2Adapter
        }
    }

    private fun updateDataList(dataList: List<Quiz2ListVhBindingModel>) {
        (binding.recyclerQuiz2.adapter as? Quiz2Adapter)?.submitList(dataList)
    }

    // endregion

    // region FAB Button

    private fun initialFabButton() {
        binding.fabAdd.setOnClickListener { viewModel.navigateToAdd(view = it) }
    }

    // endregion

    // region Loading Progress

    private fun hideLoading() {
        binding.progressBar showOrHide false
    }

    // endregion
}