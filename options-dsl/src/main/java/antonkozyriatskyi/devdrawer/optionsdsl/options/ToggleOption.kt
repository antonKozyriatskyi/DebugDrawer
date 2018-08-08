package antonkozyriatskyi.devdrawer.optionsdsl.options

import android.content.Context
import android.widget.CompoundButton
import android.widget.ToggleButton

class ToggleOption(context: Context) : DevOption(context) {

    override val view = ToggleButton(context)


    var title: String
        set(value) {
            view.text = value
        }
        get() = view.text.toString()

    var textOn: String
        set(value) {
            view.textOn = value
        }
        get() = view.textOn.toString()

    var textOff: String
        set(value) {
            view.textOff = value
        }
        get() = view.textOff.toString()

    fun onCheckedChange(listener: CompoundButton.OnCheckedChangeListener) {
        view.setOnCheckedChangeListener(listener)
    }

    inline fun onCheckedChange(crossinline listener: (isChecked: Boolean) -> Unit) {
        onCheckedChange(CompoundButton.OnCheckedChangeListener { _, isChecked -> listener(isChecked) })
    }
}