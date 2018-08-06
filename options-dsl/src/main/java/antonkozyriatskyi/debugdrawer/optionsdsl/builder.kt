package antonkozyriatskyi.debugdrawer.optionsdsl

import android.content.Context
import antonkozyriatskyi.debugdrawer.optionsdsl.options.DebugOptions

fun debugOptions(context: Context, optionsBody: DebugOptions.() -> Unit): DebugOptions {
    val debugOptions = DebugOptions(context)
    debugOptions.optionsBody()
    debugOptions.addOptionViews()
    return debugOptions
}