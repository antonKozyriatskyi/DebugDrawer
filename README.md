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
 - RadioOption
 - RadioGroupOption
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
    title = "Dangerous button" // Title can also be set like this
    onClick {
        showToast("$title clicked")
    }
}
```
 
#### CheckBoxOption

```kotlin
checkbox {
    title = "Enable logging"
    
    onCheckedChange {
        
    }
}
```
#### EditTextOption
#### RadioOption
#### RadioGroupOption
#### SpinnerOption
#### SwitchOption
#### TextOption
#### ToggleOption
#### ViewOption
#### Section
#### Divider
  