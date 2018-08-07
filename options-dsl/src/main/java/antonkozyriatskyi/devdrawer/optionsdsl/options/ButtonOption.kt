package antonkozyriatskyi.devdrawer.optionsdsl.options

import android.content.Context
import android.view.View
import android.widget.Button

class ButtonOption(context: Context) : DevOption(context) {

    override val view = Button(context)


    var title: String
        set(value) {
            view.text = value
        }
        get() = view.text.toString()

    fun onClick(listener: View.OnClickListener) {
        view.setOnClickListener(listener)
    }

    inline fun onClick(crossinline listener: (button: View) -> Unit) {
        onClick(View.OnClickListener { view -> listener(view) })
    }
}
