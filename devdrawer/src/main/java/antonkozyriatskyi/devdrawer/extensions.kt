package antonkozyriatskyi.devdrawer

import android.content.Context
import android.util.TypedValue

internal fun Context.dp2px(dp: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics).toInt()
}