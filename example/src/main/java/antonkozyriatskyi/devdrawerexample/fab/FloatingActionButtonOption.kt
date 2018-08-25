package antonkozyriatskyi.devdrawerexample.fab

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.design.widget.FloatingActionButton
import android.view.View
import antonkozyriatskyi.devdrawer.options.DevOption
import antonkozyriatskyi.devdrawer.options.DevOptions

class FloatingActionButtonOption(context: Context) : DevOption(context) {

    override val view = FloatingActionButton(context)

    fun setImageResource(@DrawableRes id: Int) {
        view.setImageResource(id)
    }

    fun onClick(listener: (view: View) -> Unit) {
        view.setOnClickListener(listener)
    }
}

fun DevOptions.fab(block: FloatingActionButtonOption.() -> Unit): FloatingActionButtonOption {
    val option = FloatingActionButtonOption(context).apply(block)

    addOption(option)

    return option
}