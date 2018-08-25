package antonkozyriatskyi.devdrawer.options

import android.content.Context
import android.widget.SeekBar

class SeekbarOption(context: Context) : DevOption(context) {

    override val view = SeekBar(context)

    fun onProgressChanged(listener: (progress: Int, fromUser: Boolean) -> Unit) {
        onProgressChanged(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                listener(progress, fromUser)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    fun onProgressChanged(listener: SeekBar.OnSeekBarChangeListener) {
        view.setOnSeekBarChangeListener(listener)
    }

}