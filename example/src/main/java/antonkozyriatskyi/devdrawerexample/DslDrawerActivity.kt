package antonkozyriatskyi.devdrawerexample

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import antonkozyriatskyi.devdrawer.DevDrawer
import antonkozyriatskyi.devdrawerexample.fab.fab

class DslDrawerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        DevDrawer.attachTo(this, gravity = Gravity.END, enableInRelease = false) {

            checkbox {
                text = "Enable logging"
                onCheckedChange { isChecked -> showToast("Logging enabled: $isChecked") }
            }

            section(addClosingDivider = true) {
                title = "Network settings"

                toggle {
                    text = "Network state"
                    textOn = "Connected"
                    textOff = "Disconnected"
                    onCheckedChange { isChecked -> showToast("Network: $isChecked") }
                }

                editText {
                    text = "localhost"
                    hint = "Server url"
                    onTextChanged { text -> showToast(text.toString()) }
                }

                checkbox {
                    text = "Mock responses"
                    onCheckedChange { isChecked -> showToast("Mock responses enabled: $isChecked") }
                }

                radioGroup {
                    radioButton(isChecked = true) {
                        text = "Send real requests"
                    }
                    radioButton {
                        text = "Show error responses only"
                    }
                    radioButton(title = "Show success responses only")

                    onCheckedChange { option ->
                        showToast("${option.text} selected")
                    }
                }
            }

            switch {
                text = "God mode"
                onCheckedChange { showToast("God mode switched: $it") }
            }

            button {
                text = "Crash"
                onClick { throw Exception("Intended crash") }
            }

            text { text = "Theme" }

            spinner {
                item { "Auto" }
                addItem("Dark")
                item { "Light" }

                onItemSelected { item, position -> showToast("$item at $position") }
            }

            view {
                val imageView = ImageView(this@DslDrawerActivity)
                imageView.setImageResource(R.mipmap.ic_launcher)
                imageView.setBackgroundColor(Color.BLACK)
                imageView
            }

            seekbar {
                onProgressChanged { progress, fromUser -> showToast("Progress: $progress") }
            }

            fab {
                setImageResource(R.mipmap.ic_launcher)

                onClick { showToast("FAB") }
            }
        }
    }
}