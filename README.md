# DebugDrawer

Drawer for 

### How to use

In your activity call:

```kotlin
val settingsView = LayoutInflater.from(this).inflate(R.layout.settings_drawer, null)
DevDrawer.attachTo(this, gravity = Gravity.END, contentView = settingsView)
```

## Kotlin DSL builder

If you're using Kotlin, you can create debug layout with nice DSL.

### Available DevOptions

 - ButtonOption
 - CheckBoxOption
 - EditTextOption
 - RadioOption and RadioGroupOption
 - SpinnerOption
 - SwitchOption
 - TextOption
 - ToggleOption
 - ViewOption
 - Section
 - Divider
 
#### ButtonOption
 
Represents a button
 
 ```kotlin
button(title = "Dangerous button") {
    text = "Dangerous button" // Title can also be set like this
    onClick {
        showToast("$title clicked")
    }
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

#### SwitchOption

```kotlin
switch {
    text = "God mode"
    isChecked = true
    onCheckedChange { checked -> showToast("God mode switched: $checked") }
}
```

#### TextOption

```kotlin
text { text = "Theme" }
```

#### ToggleOption

```kotlin
// TODO
```

#### ViewOption

```kotlin
// TODO
```

#### SpinnerOption

```kotlin
spinner {
    item { "Auto" }
    addItem("Dark")
    item { "Light" }
    
    onItemSelected { item, position -> showToast("$item at $position") }
}
```

#### Section

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

```kotlin
divider {
    thickness = 1
    color = Color.LTGRAY
}
```
  