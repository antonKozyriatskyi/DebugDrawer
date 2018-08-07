package antonkozyriatskyi.devdrawer.optionsdsl

import android.content.Context
import antonkozyriatskyi.devdrawer.optionsdsl.options.DevOptions

fun devOptions(context: Context, optionsBody: DevOptions.() -> Unit): DevOptions {
    val devOptions = DevOptions(context)
    devOptions.optionsBody()
    devOptions.addOptionViews()
    return devOptions
}