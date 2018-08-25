# DebugDrawer

Drawer that allows you to easily put options you may need to change while developing your app (e.g. 
switch between production and dev urls) into a side drawer.

It actually just wraps your activity's root layout into a `android.support.v4.widget.DrawerLayout`.

<img src="art/1.png" width="40%" /> <img src="art/2.png" width="40%" />

### How to use

In your activity or fragment call:

```kotlin
val settingsView: View = // inflate your view 
DevDrawer.attachTo(this, gravity = Gravity.END, contentView = settingsView)
```

## Kotlin DSL builder

If you're using Kotlin, you can create layout with nice DSL:

```kotlin
// Create options
val devOptions = devOptions {

    checkbox {
        text = "Enable logging"

        onCheckedChange { isChecked -> showToast("Logging enabled: $isChecked") }
    }
    
    switch {
        text = "God mode"
        onCheckedChange { isChecked -> showToast("God mode switched: $isChecked") }
    }
    
    button {
        text = "Crash"
        onClick { throw Exception("Intended crash") }
    }
    
    spinner {
        item { "Auto" }
        addItem("Dark") // another way to add item to a spinner
        item { "Light" }
        
        onItemSelected { item, position -> showToast("$item at $position") }
    }
}

// And then pass options to the `attachView` function
DevDrawer.attachTo(this, gravity = Gravity.END, contentView = devOptions.view)
```

Or you can combine that steps into one:

```kotlin
DevDrawer.attachTo(this, gravity = Gravity.END) {

    checkbox {
            text = "Enable logging"
            onCheckedChange { isChecked -> showToast("Logging enabled: $isChecked") }
        }
        
        switch {
            text = "God mode"
            onCheckedChange { isChecked -> showToast("God mode switched: $isChecked") }
        }
        
        button {
            text = "Crash"
            onClick { throw Exception("Intended crash") }
        }
        
        spinner {
            item { "Auto" }
            addItem("Dark")
            item { "Light" }
            
            onItemSelected { item, position -> showToast("$item at $position") }
        }
}
```
### Available DevOptions

 - [ButtonOption](#buttonoption)
 - [CheckBoxOption](#checkboxoption)
 - [EditTextOption](#edittextoption)
 - [RadioOption and RadioGroupOption](#radiooption-and-radiogroupoption)
 - [SpinnerOption](#spinneroption)
 - [SwitchOption](#switchoption)
 - [TextOption](#textoption)
 - [ToggleOption](#toggleoption)
 - [SeekbarOption](#seekbaroption)
 - [ViewOption](#viewoption)
 - [Section](#section)
 - [Divider](#divider)
 
 
 Visit [Creating custom option](#creating-custom-option) section, if you lack some options
 
#### ButtonOption
 
 ```kotlin
button(text = "Dangerous button") {
    text = "Dangerous button" // Title can also be set via property, this applies to all options that have text
    onClick { showToast("$title clicked") }
}
```
 
#### CheckBoxOption

```kotlin
checkbox {
    text = "Enable logging"
    onCheckedChange { isChecked -> showToast("Logging enabled: $isChecked") }
}
```

#### EditTextOption

```kotlin
editText {
     text = "localhost"
     hint = "Server url"
     onTextChanged { newText -> showToast(newText.toString()) }
}
```

#### RadioOption and RadioGroupOption

```kotlin
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
```

#### SpinnerOption

```kotlin
spinner(mode = Spinner.MODE_DROPDOWN) {
    item { "Auto" }
    addItem("Dark") // another way to add item to a spinner
    item { "Light" }
    
    onItemSelected { item, position -> showToast("$item at $position") }
}
```

#### SwitchOption

```kotlin
switch {
    text = "God mode"
    isChecked = true
    
    onCheckedChange { isChecked -> showToast("God mode switched: $isChecked") }
}
```

#### TextOption

```kotlin
text { text = "Theme" }
```

#### ToggleOption

```kotlin
toggle {
    text = "Network state"
    textOn = "Connected"
    textOff = "Disconnected"
    onCheckedChange { isChecked -> showToast("Network: $isChecked") }
}
```

#### SeekbarOption

```kotlin
seekbar {
    onProgressChanged { progress, fromUser -> showToast("Progress: $progress") }
}
```

#### ViewOption

Allows you to put any view in `DevDrawer`

```kotlin
view {
    val image = ImageView(this@DslDrawerActivity)
    image.setImageResource(R.mipmap.ic_launcher)
    image.setBackgroundColor(Color.BLACK)
    image
}
```

#### Section

Section allows you to group options related to some category.
It actually just adds divider, text under divider (title) 
then views you specified in clojure and closing divider (optionally).

```kotlin
section(addClosingDivider = false) {
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
```

#### Divider
Basically it's just a view with specified height (default is `1dp`),
color (default is `Color.LTGRAY`) and width equal to parent's width.

```kotlin
divider {
    thickness = 1
    color = Color.LTGRAY
}
```

### Creating custom option
If library doesn't provide you with option you need, you can create your own option
by subclassing `DevOption` and overriding it's `view: View` property.

#### Example
Let's create `FloatingActionButtonOption` which is not included in library by default.

```kotlin
class FloatingActionButtonOption(context: Context) : DevOption(context) {

    override val view = FloatingActionButton(context)
    
    fun setImageResource(@DrawableRes id: Int) {
        view.setImageResource(id)
    }
    
    fun onClick(listener: (view: View) -> Unit) {
        view.setOnClickListener(listener)
    }
}
```
 That's it! But for more convenient usage it'll be good to add builder function to `DevOptions`
 class.
 
 ```kotlin
fun DevOptions.fab(block: FloatingActionButtonOption.() -> Unit): FloatingActionButtonOption {
    val option = FloatingActionButtonOption(context)
    option.block()

    addOption(option)

    return option
}
```

So the callsite now looks like this:
```kotlin
DevDrawer.attachTo(this) {
    fab {
        setImageResource(R.mipmap.ic_launcher)
        onClick { showToast("FAB") }
    }
}
```

### License
```
   Copyright 2018 Anton Kozyriatskyi

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
