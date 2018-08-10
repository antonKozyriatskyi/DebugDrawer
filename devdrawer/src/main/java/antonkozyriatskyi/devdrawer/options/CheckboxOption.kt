package antonkozyriatskyi.devdrawer.options

import android.content.Context
import android.widget.CheckBox

class CheckboxOption(context: Context) : CompoundButtonOption(context) {

    override val view = CheckBox(context)
}