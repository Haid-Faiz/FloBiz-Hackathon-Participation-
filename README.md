## StackOverFloBiz
[![Platform](https://img.shields.io/badge/platform-android-blue.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-23%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=23)

### I have developed this app as an assignment given in Hackathon of FloBiz Company (It was a Hiring Challange by FloBiz).

***It's a basic StackOverFlow Clone. [Developed using StackExchange Api]***
- Latest Questions on Stackoverflow.
- Search any question.
- Filter questions according to Tags specified by you.

- **Developed by following Modern Android Development Techniques**

## ScreenShots
<table>
   <tr>
<td><img src = "https://user-images.githubusercontent.com/56159740/138890312-758b7ab9-fcbf-47c4-a1e5-74981cf57843.gif" height = "370" width="200"></td>
<td><img src = "https://user-images.githubusercontent.com/56159740/138890320-c3b4ce20-ecc5-4364-b07f-4207aae22c1c.gif" height = "370" width="200"></td>
<td><img src = "https://user-images.githubusercontent.com/56159740/138890328-45a10130-a164-4188-9409-8ca6a8ea1ffd.gif" height = "370" width="200"></td>
  </tr>
</table>   
     

## Architecture
The project follows the MVVM structure with Modular Architecture without any specifics.

There are two _modules_ in the project

* `app` - The UI of the app. The main project that forms the APK
* `datastore` - The REST API consumption and local database android library

###  The following libraries were used in this project.

- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- [Dagger-Hilt](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Jetpack Navigation](https://developer.android.com/guide/navigation) - Android Jetpack's Navigation component helps you implement navigation, from simple button clicks to more complex patterns.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) - A Converter which uses Moshi for serialization to and from JSON.
- [Coil-kt](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.
- [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Jetpack DataStore is a data storage solution that allows you to
 store key-value pairs. Basically it's a replacement for SharedPreferences.
- [Chrome Custom Tabs](https://developer.chrome.com/docs/android/custom-tabs/overview/) - Custom Tabs is a browser feature, introduced by Chrome. We can use it like WebView.
