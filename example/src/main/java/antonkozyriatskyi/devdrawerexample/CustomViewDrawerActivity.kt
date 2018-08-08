package antonkozyriatskyi.devdrawerexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import antonkozyriatskyi.devdrawer.DevDrawer

class CustomViewDrawerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_drawer)

        val view = LayoutInflater.from(this).inflate(R.layout.settings_drawer, null)
        DevDrawer.attachTo(this, gravity = Gravity.END, contentView = view)
    }
}
