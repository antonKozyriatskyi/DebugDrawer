package antonkozyriatskyi.devdrawer.options

import android.content.Context
import android.widget.Switch

class SwitchOption(context: Context) : CompoundButtonOption(context) {

    override val view = Switch(context)

}
