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

    inline fun switch(text: String = "",
                      isChecked: Boolean = false,
                      block: SwitchOption.() -> Unit = {}): SwitchOption {
        val option = SwitchOption(context)
        option.text = text
        option.isChecked = isChecked
        option.block()

        addOption(option)

        return option
    }

    inline fun checkbox(text: String = "",
                        isChecked: Boolean = false,
                        block: CheckboxOption.() -> Unit = {}): CheckboxOption {
        val option = CheckboxOption(context)
        option.text = text
        option.isChecked = isChecked
        option.block()

        addOption(option)

        return option
    }

    inline fun editText(text: String = "",
                        hint: String = "",
                        block: EditTextOption.() -> Unit = {}): EditTextOption {
        val option = EditTextOption(context)
        option.text = text
        option.hint = hint
        option.block()

        addOption(option)

        return option
    }

    inline fun section(title: String = "",
                @ColorInt dividerColor: Int = Color.LTGRAY,
                addClosingDivider: Boolean = false,
                block: Section.() -> Unit): Section {
        val option = Section(context, dividerColor, addClosingDivider)
        option.title = title
        option.block()
        option.addOptionViews()

        addOption(option)

        return option
    }

    inline fun button(text: String = "",
                      block: ButtonOption.() -> Unit): ButtonOption {
        val option = ButtonOption(context)
        option.text = text
        option.block()

        addOption(option)

        return option
    }

    inline fun text(text: String = "",
                    block: TextOption.() -> Unit = {}): TextOption {
        val option = TextOption(context)
        option.text = text
        option.block()

        addOption(option)

        return option
    }

    inline fun toggle(text: String = "",
                      textOn: String? = null,
                      textOff: String? = null,
                      isChecked: Boolean = false,
                      block: ToggleOption.() -> Unit = {}): ToggleOption {
        val option = ToggleOption(context)
        option.text = text
        option.isChecked = isChecked
        if (textOn != null) option.textOn = textOn
        if (textOff != null) option.textOff = textOff
        option.block()

        addOption(option)

        return option
    }

    inline fun view(block: () -> View): ViewOption {
        val option = ViewOption(block(), context)

        addOption(option)

        return option
    }

    inline fun radioGroup(block: RadioGroupOption.() -> Unit): RadioGroupOption {
        val option = RadioGroupOption(context)
        option.block()
        option.addOptionViews()

        addOption(option)

        return option
    }

    val divider: DividerOption
        get() = divider()

    inline fun divider(@ColorInt color: Int = Color.LTGRAY,
                       @Dimension(unit = Dimension.DP) thickness: Int = 1,
                       block: DividerOption.() -> Unit = {}): DividerOption {
        val option = DividerOption(color, thickness, context)
        option.block()

        addOption(option)

        return option
    }

    inline fun spinner(block: SpinnerOption.() -> Unit): SpinnerOption {
        val option = SpinnerOption(context)
        option.block()

        addOption(option)

        return option
    }

    fun addOption(option: DevOption) {
        options.add(option)
    }

    open fun addOptionViews() {
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