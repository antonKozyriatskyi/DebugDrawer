# DebugDrawer

Drawer for 

### How to use

In your activity call:

```kotlin
 DebugDrawer.attachTo(this, gravity = Gravity.END) {
             switch {
                 title = "Switch 1"
                 onCheckedChange {
                     showToast("$title: isChecked = $it")
                 }
             }
             switch(title = "Switch 2") {
                 onCheckedChange {
                     showToast("$title: isChecked = $it")
                 }
             }
 
             section(title = "Section 1") {
                 checkbox("Checkbox 1") {
                     onCheckedChange {
                         showToast("$title: isChecked = $it")
                     }
                 }
             }
 
             section("Section 2") {
                 editText(title = "FOO") {
                     hint = "Hint"
                     val toast = toast("")
                     onTextChanged {
                         println(it)
                         toast.cancel()
                         toast.setText("EditText in $title\n$it")
                         toast.show()
                     }
                 }
             }
         }
```