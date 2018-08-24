package antonkozyriatskyi.devdrawer

import android.app.Activity
import android.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import antonkozyriatskyi.devdrawer.options.DevOptions
import android.support.v4.app.Fragment as SupportFragment

class DevDrawer(private val activity: Activity, var enableInRelease: Boolean) {

    companion object {

        @JvmStatic
        fun attachTo(activity: Activity, @DrawerGravity gravity: Int = Gravity.END,
                     enableInRelease: Boolean = false, contentView: View) {
            val devDrawer = DevDrawer(activity, enableInRelease)
            devDrawer.createDrawer(gravity, contentView)
        }

        @JvmStatic
        fun attachTo(fragment: SupportFragment, @DrawerGravity gravity: Int = Gravity.END,
                     enableInRelease: Boolean = false, contentView: View) {
            attachTo(fragment.activity!!, gravity, enableInRelease, contentView)
        }

        @JvmStatic
        fun attachTo(fragment: Fragment, @DrawerGravity gravity: Int = Gravity.END,
                     enableInRelease: Boolean = false, contentView: View) {
            attachTo(fragment.activity!!, gravity, enableInRelease, contentView)
        }

        fun attachTo(activity: Activity, @DrawerGravity gravity: Int = Gravity.END,
                     enableInRelease: Boolean = false, optionsBody: DevOptions.() -> Unit) {
            val options = devOptions(activity, optionsBody)
            val contentView = options.view
            attachTo(activity, gravity, enableInRelease, contentView)
        }

        fun attachTo(fragment: SupportFragment, @DrawerGravity gravity: Int = Gravity.END,
                     enableInRelease: Boolean = false, devOptions: DevOptions.() -> Unit) {
            attachTo(fragment.activity!!, gravity, enableInRelease, devOptions)
        }

        fun attachTo(fragment: Fragment, @DrawerGravity gravity: Int = Gravity.END,
                     enableInRelease: Boolean = false, devOptions: DevOptions.() -> Unit) {
            attachTo(fragment.activity!!, gravity, enableInRelease, devOptions)
        }
    }


    fun createDrawer(@DrawerGravity gravity: Int, contentView: View) {

        // do not add drawer if it's not debug build and enableInRelease == false
        if (enableInRelease.not() and BuildConfig.DEBUG.not()) return

        val root = activity.window.decorView.findViewById<ViewGroup>(android.R.id.content)
        val activityRootView = root.getChildAt(0)

        val drawerLayout = DrawerLayout(activity)
        drawerLayout.layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)

        var contentLayoutParams: ViewGroup.LayoutParams? = contentView.layoutParams
        if (contentLayoutParams == null || contentLayoutParams !is DrawerLayout.LayoutParams) {
            contentLayoutParams = DrawerLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            contentView.layoutParams = contentLayoutParams
        }

        contentLayoutParams.gravity = gravity

        root.removeView(activityRootView)
        root.addView(drawerLayout)

        drawerLayout.addView(activityRootView)
        drawerLayout.addView(contentView)
    }
}