package antonkozyriatskyi.devdrawer.optionsdsl.options

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RadioGroup
import antonkozyriatskyi.devdrawer.optionsdsl.dp2px

class RadioOptions(context: Context) : DevOptions(context) {

    override val view = RadioGroup(context)


    inline fun radioButton(title: String = "",
                           isChecked: Boolean = false,
                           block: RadioOption.() -> Unit): RadioOption {
        val option = RadioOption(context)
        option.isChecked = isChecked
        option.title = title
        option.block()

        addOption(option)

        return option
    }

    fun onCheckedChange(listener: RadioGroup.OnCheckedChangeListener) {
        view.setOnCheckedChangeListener(listener)
    }

    inline fun onCheckedChange(crossinline listener: (radioOption: RadioOption) -> Unit) {
        onCheckedChange(RadioGroup.OnCheckedChangeListener { _, checkedId ->
            val selectedOption = (options.first { it.id == checkedId } as RadioOption)
            listener(selectedOption)
        })
    }

    override fun addOptionViews() {

        val dp8 = context.dp2px(8)

        for (i in options.indices) {
            val option = options[i]
            val layoutParams = RadioGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            layoutParams.topMargin = dp8

            val optionView = option.view
            optionView.layoutParams = layoutParams

            view.addView(optionView)

            val isChecked = (option as RadioOption).isChecked
            if (isChecked) {
                view.check(option.view.id)
            }
        }
    }
}