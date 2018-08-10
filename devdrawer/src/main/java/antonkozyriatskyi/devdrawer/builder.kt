package antonkozyriatskyi.devdrawer

import android.content.Context
import antonkozyriatskyi.devdrawer.options.DevOptions

fun devOptions(context: Context, optionsBody: DevOptions.() -> Unit): DevOptions {
    val devOptions = DevOptions(context)
    devOptions.optionsBody()
    devOptions.addOptionViews()
    return devOptions
}