# Alwan
Alwan is an Android Jetpack Compose color picker library.  

## Preview
https://user-images.githubusercontent.com/12600936/189493581-ae1d92ea-143f-4b0f-9d62-5b6e097118ba.mp4

![image](https://user-images.githubusercontent.com/12600936/189495507-1f0fe171-7e48-4696-ae49-61e1bab3dc4e.png)


## Download
#### Gradle:
```gradle
dependencies {
  implementation 'com.raedapps:alwan:1.0.1'
}
```

## Usage Guide
You can use `Alwan` composable as following:
````Kotlin
Alwan(
  onColorChanged = { color -> },
  modifier = Modifier.width(300.dp),
)
````
`onColorChanged` is called whenever the user selects a new color.

### Providing the default color:
Use AlwanState to control the initially selected color:
````Kotlin
Alwan(
  onColorChanged = { },
  modifier = Modifier.width(300.dp),
  state = rememberAlwanState(initialColor = Color.Yellow),
)
````

### Showing the alpha slider
The alpha slider is hidden by default. Use the `showAlphaSlider` parameter to show it:
```kotlin
Alwan(
  onColorChanged = { },
  showAlphaSlider = true,
)
```

### Using AlwanDialog
You can use the `AlwanDialog` as following:
````kotlin
AlwanDialog(
  onColorChanged = { },
  onDismissRequest = { },
)
````

`AlwanDialog` can be customized with positive & negative buttons:
````kotlin
AlwanDialog(
  onDismissRequest = { },
  onColorChanged = { },
  positiveButtonText = "OK",
  onPositiveButtonClick = { },
  negativeButtonText = "CANCEL",
  onNegativeButtonClick = { },
)
````


## License
```
Copyright 2022 Raed Mughaus

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
