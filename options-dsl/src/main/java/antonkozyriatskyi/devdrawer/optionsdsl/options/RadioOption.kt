package antonkozyriatskyi.devdrawer.optionsdsl.options

import android.content.Context
import android.widget.CompoundButton
import android.widget.RadioButton

class RadioOption(context: Context) : DevOption(context) {

    override val view = RadioButton(context)


    var title: String
        set(value) {
            view.text = value
        }
        get() = view.text.toString()

    fun onCheckedChange(listener: CompoundButton.OnCheckedChangeListener) {
        view.setOnCheckedChangeListener(listener)
    }

    inline fun onCheckedChange(crossinline listener: (isChecked: Boolean) -> Unit) {
        onCheckedChange(CompoundButton.OnCheckedChangeListener { _, isChecked -> listener(isChecked) })
    }
}