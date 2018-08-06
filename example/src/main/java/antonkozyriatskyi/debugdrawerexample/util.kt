package antonkozyriatskyi.debugdrawerexample

import android.content.Context
import android.widget.Toast


fun Context.showToast(msg: String) {
    toast(msg).show()
}

fun Context.toast(msg: String): Toast {
    return Toast.makeText(this, msg, Toast.LENGTH_SHORT)
}