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
        }
    }
}