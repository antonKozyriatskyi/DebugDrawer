package antonkozyriatskyi.devdrawer.options

import android.content.Context
import android.graphics.Typeface
import android.support.annotation.ColorInt
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import antonkozyriatskyi.devdrawer.dp2px

class Section(context: Context,
              @ColorInt var dividerColor: Int,
              var addClosingDivider: Boolean) : DevOptions(context) {

    override val view: ViewGroup = LinearLayout(context).also {
        it.orientation = LinearLayout.VERTICAL
        val layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        val dp8 = context.dp2px(8)
        it.layoutParams = layoutParams
        it.setPadding(0, dp8, 0, dp8)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    val titleView = TextView(context).also {
        val layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        val dp8 = context.dp2px(8)
        layoutParams.topMargin = dp8
        layoutParams.leftMargin = dp8
        it.layoutParams = layoutParams
        it.typeface = Typeface.DEFAULT_BOLD
    }

    init {
        view.addView(divider())
        view.addView(titleView)
    }

    var title: String
        set(value) {
            titleView.text = value
        }
        get() = titleView.text.toString()

    override fun addOptionViews() {
        val contentLayout = view

        val dp8 = context.dp2px(8)

        for (option in options) {
            val optionView = option.view

            if (optionView.layoutParams == null) {
                val layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                layoutParams.topMargin = dp8
                optionView.layoutParams = layoutParams
            }

            contentLayout.addView(optionView)
        }

        if (addClosingDivider) contentLayout.addView(divider())
    }

    private fun divider(): View {
        val dividerView = View(context).apply {
            val params = LinearLayout.LayoutParams(MATCH_PARENT, context.dp2px(1))
            params.topMargin = context.dp2px(8)
            layoutParams = params
            setBackgroundColor(dividerColor)
        }

        return dividerView
    }
}