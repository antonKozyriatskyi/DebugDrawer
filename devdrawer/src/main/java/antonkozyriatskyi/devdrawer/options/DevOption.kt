package antonkozyriatskyi.devdrawer.options

import android.content.Context
import android.view.View

abstract class DevOption(val context: Context) {

    abstract val view: View

    var id: Int
        set(value) {
            view.id = value
        }
        get() = view.id

    val width: Int
        get() = view.width

    val height: Int
        get() = view.height
}