package personal.ivan.migo_quiz.feature.quiz2.view.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import personal.ivan.migo_quiz.R
import personal.ivan.migo_quiz.databinding.VhQuiz2Binding
import personal.ivan.migo_quiz.feature.quiz2.model.Quiz2ListVhBindingModel
import personal.ivan.migo_quiz.io.model.Quiz2Info
import personal.ivan.migo_quiz.util.textColorByResId

class Quiz2Adapter :
    ListAdapter<Quiz2ListVhBindingModel, Quiz2Adapter.Quiz2ViewHolder>(DIFF_CALLBACK) {

    // region Click Listener

    private var itemListener: View.OnClickListener? = null
    private var activateListener: View.OnClickListener? = null

    /**
     * Listener for whole item click
     */
    fun setOnItemClickListener(listener: View.OnClickListener?) {
        itemListener = listener
    }

    /**
     * Listener for activate button
     */
    fun setOnActivateClickListener(listener: View.OnClickListener?) {
        activateListener = listener
    }

    // endregion

    // region Override

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Quiz2ViewHolder = Quiz2ViewHolder(
        binding = VhQuiz2Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: Quiz2ViewHolder,
        position: Int
    ) {
        holder.bind(
            model = getItem(position),
            itemListener = itemListener,
            activateListener = activateListener
        )
    }

    // endregion

    // region Diff

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Quiz2ListVhBindingModel>() {
            override fun areItemsTheSame(
                oldItem: Quiz2ListVhBindingModel,
                newItem: Quiz2ListVhBindingModel
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: Quiz2ListVhBindingModel,
                newItem: Quiz2ListVhBindingModel
            ): Boolean = oldItem.hashCode() == newItem.hashCode()
        }
    }

    // endregion

    // region View Holder

    class Quiz2ViewHolder(private val binding: VhQuiz2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(
            model: Quiz2ListVhBindingModel,
            itemListener: View.OnClickListener?,
            activateListener: View.OnClickListener?
        ) {
            binding.apply {
                cardView.setOnClickListener(itemListener)
                cardView.tag = model

                textViewPass.text = model.pass
                textViewPass textColorByResId model.statusColor

                val type = root.context.getString(
                    if (model.type == Quiz2Info.QUIZ_2_TYPE_HOUR) R.string.button_text_hour
                    else R.string.button_text_day
                )
                textViewDescription.text = "${model.value} $type"
                textViewDescription textColorByResId model.statusColor

                textViewStatus.setText(model.status)
                textViewStatus textColorByResId model.statusColor

                buttonActivate.tag = model
                buttonActivate.visibility = if (model.showActivation) View.VISIBLE else View.GONE
                buttonActivate.setOnClickListener(activateListener)
            }
        }
    }

    // endregion
}