package antonkozyriatskyi.devdrawer.optionsdsl.options

import android.content.Context
import android.widget.ToggleButton

class ToggleOption(context: Context) : CompoundButtonOption(context) {

    override val view = ToggleButton(context)


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
}