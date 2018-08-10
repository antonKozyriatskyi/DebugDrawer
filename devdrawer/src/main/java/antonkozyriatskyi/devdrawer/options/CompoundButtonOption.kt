package antonkozyriatskyi.devdrawer.options

import android.content.Context
import android.widget.CompoundButton

abstract class CompoundButtonOption(context: Context) : DevOption(context) {

    abstract override val view: CompoundButton

    var text: String
        set(value) {
            view.text = value
        }
        get() = view.text.toString()

    var isChecked: Boolean
        set(value) {
            view.isChecked = value
        }
        get() = view.isChecked

    fun onCheckedChange(listener: CompoundButton.OnCheckedChangeListener) {
        view.setOnCheckedChangeListener(listener)
    }

    fun onCheckedChange(listener: (isChecked: Boolean) -> Unit) {
        onCheckedChange(CompoundButton.OnCheckedChangeListener { _, isChecked -> listener(isChecked) })
    }
}