package antonkozyriatskyi.debugdrawer

import android.app.Activity
import android.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import antonkozyriatskyi.debugdrawer.optionsdsl.debugOptions
import antonkozyriatskyi.debugdrawer.optionsdsl.options.DebugOptions
import android.support.v4.app.Fragment as SupportFragment

class DebugDrawer(private val activity: Activity) {

    companion object {

        @JvmStatic
        fun attachTo(activity: Activity, @DrawerGravity gravity: Int = Gravity.END, contentView: View) {
            val debugDrawer = DebugDrawer(activity)
            debugDrawer.createDrawer(gravity, contentView)
        }

        @JvmStatic
        fun attachTo(fragment: SupportFragment, @DrawerGravity gravity: Int = Gravity.END, contentView: View) {
            attachTo(fragment.activity!!, gravity, contentView)
        }

        @JvmStatic
        fun attachTo(fragment: Fragment, @DrawerGravity gravity: Int = Gravity.END, contentView: View) {
            attachTo(fragment.activity!!, gravity, contentView)
        }

        fun attachTo(activity: Activity, @DrawerGravity gravity: Int = Gravity.END, optionsBody: DebugOptions.() -> Unit) {
            val options = debugOptions(activity, optionsBody)
            val contentView = options.view
            attachTo(activity, gravity, contentView)
        }

        fun attachTo(fragment: SupportFragment, @DrawerGravity gravity: Int = Gravity.END, debugOptions: DebugOptions.() -> Unit) {
            attachTo(fragment.activity!!, gravity, debugOptions)
        }

        fun attachTo(fragment: Fragment, @DrawerGravity gravity: Int = Gravity.END, debugOptions: DebugOptions.() -> Unit) {
            attachTo(fragment.activity!!, gravity, debugOptions)
        }
    }


    fun createDrawer(@DrawerGravity gravity: Int, contentView: View) {

        val root = activity.window.decorView.findViewById<ViewGroup>(android.R.id.content)
        val activityRootView = root.getChildAt(0)

        val drawerLayout = DrawerLayout(activity)
        drawerLayout.layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)

        (contentView.layoutParams as DrawerLayout.LayoutParams).gravity = gravity

        root.removeView(activityRootView)
        root.addView(drawerLayout)

        drawerLayout.addView(activityRootView)
        drawerLayout.addView(contentView)
    }
}