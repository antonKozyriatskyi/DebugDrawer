package antonkozyriatskyi.devdrawer.optionsdsl.options

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner

class SpinnerOption(context: Context) : DevOption(context) {

    override val view = Spinner(context)

    var items: ArrayList<String> = arrayListOf()
        set(value) {
            field = value
            adatper.clear()
            adatper.addAll(items)
        }

    val adatper = ArrayAdapter(context, android.R.layout.simple_list_item_1, android.R.id.text1,
            items).also {
        it.setNotifyOnChange(true)
        it.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
    }

    fun addItem(title: String) {
        items.add(title)
        adatper.notifyDataSetChanged()
    }

    inline fun item(block: () -> String) {
        addItem(block())
    }
}