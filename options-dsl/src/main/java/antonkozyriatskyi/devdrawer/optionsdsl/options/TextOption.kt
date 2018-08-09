package antonkozyriatskyi.devdrawer.optionsdsl.options

import android.content.Context
import android.widget.TextView

class TextOption(context: Context) : DevOption(context) {

    override val view = TextView(context)


    var text: String
        set(value) {
            view.text = value
        }
        get() = view.text.toString()
}