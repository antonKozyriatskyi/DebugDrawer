package antonkozyriatskyi.devdrawer.options

import android.content.Context
import android.widget.RadioButton

class RadioOption(context: Context) : DevOption(context) {

    override val view = RadioButton(context)

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
}