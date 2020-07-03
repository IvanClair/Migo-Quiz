@file:JvmName("UiExtension")

package personal.ivan.migo_quiz.util

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.textview.MaterialTextView

/**
 * Set text color by color resource ID
 */
infix fun MaterialTextView.textColorByResId(@ColorRes colorRes: Int) {
    setTextColor(ContextCompat.getColor(context, colorRes))
}

/**
 * Change visibility
 */
infix fun View.showOrHide(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}