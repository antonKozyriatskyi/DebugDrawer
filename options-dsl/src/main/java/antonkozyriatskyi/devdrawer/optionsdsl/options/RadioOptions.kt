package antonkozyriatskyi.devdrawer.optionsdsl.options

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RadioGroup
import antonkozyriatskyi.devdrawer.optionsdsl.dp2px

class RadioOptions(context: Context) : DevOptions(context) {

    override val view = RadioGroup(context)


    inline fun radioButton(title: String = "", block: RadioOption.() -> Unit): RadioOption {
        val option = RadioOption(context)
        option.title = title
        option.block()

        addOption(option)

        return option
    }

    override fun addOptionViews() {

        val dp8 = context.dp2px(8)

        for (option in options) {
            val layoutParams = RadioGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            layoutParams.topMargin = dp8

            val optionView = option.view
            optionView.layoutParams = layoutParams

            view.addView(optionView)
        }
    }
}