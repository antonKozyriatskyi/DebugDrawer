package antonkozyriatskyi.devdrawer.optionsdsl.options

import android.content.Context
import android.widget.CompoundButton
import android.widget.Switch

class SwitchOption(context: Context) : DevOption(context) {

    override val view = Switch(context)


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