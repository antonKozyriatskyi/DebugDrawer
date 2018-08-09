package antonkozyriatskyi.devdrawerexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import antonkozyriatskyi.devdrawer.DevDrawer

class DslDrawerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        DevDrawer.attachTo(this, gravity = Gravity.END) {

            checkbox {
                title = "Enable logging"
                onCheckedChange { isChecked -> showToast("Logging enabled: $isChecked") }
            }

            section(addClosingDivider = false) {
                title = "Network settings"

                toggle {
                    title = "Network state"
                    textOn = "Connected"
                    textOff = "Disconnected"
                    onCheckedChange { isChecked -> showToast("Network: $isChecked") }
                }

                editText {
                    title = "localhost"
                    hint = "Server url"
                }

                checkbox {
                    title = "Mock responses"
                    onCheckedChange { showToast("Mock responses enabled: $it") }
                }

                radioGroup {
                    radioButton(isChecked = true) {
                        title = "Send real requests"
                    }
                    radioButton {
                        title = "Show error responses only"
                    }
                    radioButton(title = "Show success responses only")

                    onCheckedChange { option ->
                        showToast("${option.title} selected")
                    }
                }
            }

            switch {
                title = "God mode"

                onCheckedChange { showToast("God mode switched: $it") }
            }

            button {
                title = "Crash"

                onClick { throw Exception("Intended crash") }
            }

            text { title = "Theme" }

            spinner {
                item { "Auto" }
                addItem("Dark")
                item { "Light" }

                onItemSelected { item, position -> showToast("$item at $position") }
            }
        }
    }
}