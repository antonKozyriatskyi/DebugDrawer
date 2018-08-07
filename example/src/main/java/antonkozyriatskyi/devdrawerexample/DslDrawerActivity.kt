package antonkozyriatskyi.devdrawerexample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import antonkozyriatskyi.devdrawer.DevDrawer

class DslDrawerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        DevDrawer.attachTo(this, gravity = Gravity.END) {

            switch {
                title = "Enable logging"
                onCheckedChange { isChecked -> showToast("Logging enabled: $isChecked") }
            }

            switch(title = "Enable something else") {
                onCheckedChange { isChecked -> showToast("Something's enabled: $isChecked") }
            }

            section {
                title = "Server settings"

                editText {
                    title = "localhost"
                    hint = "url"
                }

                checkbox {
                    title = "Mock responses"
                    onCheckedChange { showToast("Mock responses enabled: $it") }
                }
            }

            button {
                title = "My button"
            }

            text {
                title = "Custom text"
            }

            toggle {
                title = "This is toggle"
                onCheckedChange { showToast("Toggle toggled: $it") }
            }

            view {
                View(this@DslDrawerActivity).also {
                    it.setBackgroundColor(Color.GREEN)
                    it.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, 300)
                }
            }

            radioGroup {
                radioButton {
                    title = "Radio 1"
                }
                radioButton {
                    title = "Radio 2"
                }
            }

            text {
                title = "Custom text"
            }

            divider()

            text {
                title = "Custom text"
            }

            divider(thickness = 4) {

            }

            text {
                title = "Custom text"
            }

            divider {
                thickness = 3
            }
        }
    }
}