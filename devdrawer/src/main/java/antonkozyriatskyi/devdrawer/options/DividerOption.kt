package antonkozyriatskyi.devdrawer.options

import android.content.Context
import android.support.annotation.ColorInt
import android.support.annotation.Dimension
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import antonkozyriatskyi.devdrawer.dp2px

class DividerOption(@ColorInt color: Int,
                    @Dimension(unit = Dimension.DP) thickness: Int, context: Context) : DevOption(context) {

    override val view: View = View(context).apply {
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, context.dp2px(thickness))
        layoutParams = params
        setBackgroundColor(color)
    }

    @ColorInt
    var color: Int = color
        set(value) {
            view.setBackgroundColor(color)
            field = value
        }

    @Dimension(unit = Dimension.DP)
    var thickness: Int = thickness
        set(value) {
            view.layoutParams.height = context.dp2px(value)
            field = value
        }
}