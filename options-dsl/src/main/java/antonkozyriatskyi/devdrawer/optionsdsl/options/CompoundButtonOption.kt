package antonkozyriatskyi.devdrawer.optionsdsl.options

import android.content.Context
import android.widget.CompoundButton

abstract class CompoundButtonOption(context: Context) : DevOption(context) {

    abstract override val view: CompoundButton

    var title: String
        set(value) {
            view.text = value
        }
        get() = view.text.toString()

    fun onCheckedChange(listener: CompoundButton.OnCheckedChangeListener) {
        view.setOnCheckedChangeListener(listener)
    }

    fun onCheckedChange(listener: (isChecked: Boolean) -> Unit) {
        onCheckedChange(CompoundButton.OnCheckedChangeListener { _, isChecked -> listener(isChecked) })
    }
}