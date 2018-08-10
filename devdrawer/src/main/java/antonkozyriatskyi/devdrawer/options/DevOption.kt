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

    var width: Int
        set(value) {
            view.layoutParams.width = value
        }
        get() = view.layoutParams.width

    var height: Int
        set(value) {
            view.layoutParams.height = value
        }
        get() = view.layoutParams.height
}