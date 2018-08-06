package antonkozyriatskyi.debugdrawer.optionsdsl.options

import android.content.Context
import android.widget.CheckBox
import android.widget.CompoundButton

class CheckboxOption(context: Context) : DebugOption(context) {

    override val view = CheckBox(context)


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