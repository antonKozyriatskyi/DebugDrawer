package antonkozyriatskyi.devdrawer.optionsdsl.options

import android.content.Context
import android.view.View

abstract class DevOption(protected val context: Context) {
    abstract val view: View
}