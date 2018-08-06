package antonkozyriatskyi.debugdrawer.optionsdsl.options

import android.content.Context
import android.view.View

abstract class DebugOption(context: Context) {
    abstract val view: View
}