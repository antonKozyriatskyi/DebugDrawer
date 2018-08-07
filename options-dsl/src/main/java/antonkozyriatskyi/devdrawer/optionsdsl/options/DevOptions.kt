package antonkozyriatskyi.devdrawer.optionsdsl.options

import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.Dimension
import android.support.v4.widget.DrawerLayout
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import antonkozyriatskyi.devdrawer.optionsdsl.dp2px
import java.util.*

open class DevOptions(context: Context) : DevOption(context) {

    val options = LinkedList<DevOption>()

    override val view: ViewGroup

    init {
        val contentLayout = LinearLayout(context).also {
            it.orientation = LinearLayout.VERTICAL
            val layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            val dp16 = context.dp2px(16)
            it.layoutParams = layoutParams

            it.setBackgroundColor(Color.WHITE)
            it.setPadding(dp16, dp16, dp16, dp16)
        }

        val rootView = ScrollView(context).also {
            it.layoutParams = DrawerLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            it.isFillViewport = true
        }

        rootView.addView(contentLayout)

        view = rootView
    }

    inline fun switch(title: String = "", block: SwitchOption.() -> Unit): SwitchOption {
        val option = SwitchOption(context)
        option.title = title
        option.block()

        addOption(option)

        return option
    }

    inline fun checkbox(title: String = "", block: CheckboxOption.() -> Unit): CheckboxOption {
        val option = CheckboxOption(context)
        option.title = title
        option.block()

        addOption(option)

        return option
    }

    inline fun editText(title: String = "", hint: String = "", block: EditTextOption.() -> Unit): EditTextOption {
        val option = EditTextOption(context)
        option.title = title
        option.hint = hint
        option.block()

        addOption(option)

        return option
    }

    fun section(title: String = "", @ColorInt dividerColor: Int = Color.LTGRAY, block: Section.() -> Unit): Section {
        val option = Section(context, dividerColor)
        option.title = title
        option.block()
        option.addOptionViews()

        addOption(option)

        return option
    }

    inline fun button(title: String = "", block: ButtonOption.() -> Unit): ButtonOption {
        val option = ButtonOption(context)
        option.title = title
        option.block()

        addOption(option)

        return option
    }

    inline fun text(title: String = "", block: TextOption.() -> Unit): TextOption {
        val option = TextOption(context)
        option.title = title
        option.block()

        addOption(option)

        return option
    }

    inline fun toggle(title: String = "", block: ToggleOption.() -> Unit): ToggleOption {
        val option = ToggleOption(context)
        option.title = title
        option.block()

        addOption(option)

        return option
    }

    inline fun view(block: () -> View): ViewOption {
        val option = ViewOption(block(), context)

        addOption(option)

        return option
    }

    fun radioGroup(block: RadioOptions.() -> Unit): RadioOptions {
        val option = RadioOptions(context)
        option.block()
        option.addOptionViews()

        addOption(option)

        return option
    }

    inline fun divider(@ColorInt color: Int = Color.LTGRAY,
                       @Dimension(unit = Dimension.DP) thickness: Int = 1,
                       block: DividerOption.() -> Unit = { }): DividerOption {
        val option = DividerOption(color, thickness, context)
        option.block()

        addOption(option)

        return option
    }

    fun addOption(option: DevOption) {
        options.add(option)
    }

    internal open fun addOptionViews() {
        val contentLayout = (view.getChildAt(0) as ViewGroup)

        val dp8 = context.dp2px(8)

        for (option in options) {
            val optionView = option.view

            if (optionView.layoutParams == null) {
                val layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                layoutParams.topMargin = dp8
                optionView.layoutParams = layoutParams
            }

            contentLayout.addView(optionView)
        }
    }
}