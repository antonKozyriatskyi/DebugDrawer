package antonkozyriatskyi.devdrawer.options

import android.content.Context
import android.widget.SeekBar

class SeekbarOption(context: Context) : DevOption(context) {

    override val view = SeekBar(context)

    var progress: Int
        get() = view.progress
        set(value) {
            view.progress = value
        }

    var maxProgress: Int
        get() = view.max
        set(value) {
            view.max = value
        }

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