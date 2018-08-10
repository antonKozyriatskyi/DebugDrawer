package antonkozyriatskyi.devdrawer.options

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class EditTextOption(context: Context) : DevOption(context) {

    override val view = EditText(context)


    var text: String
        set(value) {
            view.setText(value)
        }
        get() = view.text.toString()

    var hint: String
        set(value) {
            view.hint = value
        }
        get() = view.hint.toString()


    inline fun onTextChanged(crossinline listener: (text: CharSequence) -> Unit) {
        view.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {
                listener(text)
            }
        })
    }
}