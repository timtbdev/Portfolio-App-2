# Portfolio <img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/kotlin.png?alt=media&token=7e4e5ac0-d101-4f97-9f20-c5e1cb36eafe" height="20"> <img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/jetpack.png?alt=media&token=da5e9d49-7495-4fe1-8e0d-43894e99f5d2" width="25"> [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)


## 📱 PORTFOLIO APP

<img src="https://firebasestorage.googleapis.com/v0/b/portfolio-app-147b5.appspot.com/o/app_icon_new.png?alt=media&token=2ccaa4c8-081d-40e4-9de9-cf08486b6c13" align="left" width="200" hspace="10" vspace="10">
👋Hello, My name is Tumur Bazarragchaa and you can call me Alex. 
I'm an Android Developer, specializing in the user interface, and with three years of experience in Kotlin, Java. 
I've published two Kotlin Android apps and up to date with the latest technologies such as Android Jetpack Components and Kotlin Coroutines. I built this app to show my skills and passion for design and development. </br>
<a href="https://tumur.me/about.html" taget="_blank">You can read more about me here</a><br/></br>
<i>Ps: This is an end result of my 1.5 years of learning of Kotlin and Android Jetpack components, and I've listed some of the resources which I found useful.</i>

<br/>
<div style="display:flex;" >
<a target="_blank" href="#">
    <img alt="Get it on Google Play"
        height="80"
        src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" />
</a>
<a target="_blank" href="https://tumur.me">
    <img alt="Visit Web"
        height="80"
        src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/visit_web.png?alt=media&token=c2c56c2e-f891-4761-b430-731840eda9a0" />
</a>
</div>
</br>

## 📽️ VIDEO

[![Little red ridning hood](http://i.imgur.com/7YTMFQp.png)](https://vimeo.com/3514904 "Little red riding hood - Click to Watch!")

## 📸 SCREENSHOTS

<img src="https://firebasestorage.googleapis.com/v0/b/portfolio-app-147b5.appspot.com/o/s-01.jpg?alt=media&token=4116e8ed-8cb9-4e41-8f3b-1af8c6bfb89f" width="300"/> <img src="https://firebasestorage.googleapis.com/v0/b/portfolio-app-147b5.appspot.com/o/s-01.jpg?alt=media&token=4116e8ed-8cb9-4e41-8f3b-1af8c6bfb89f" width="300"/>

## 📐 ARCHITECTURE (MVVM)
<img src="https://firebasestorage.googleapis.com/v0/b/personal-website-76368.appspot.com/o/architecture.png?alt=media&token=c66d207c-c9c4-4c17-9aaa-f78ca2c4e844"/>

## 📊 FEATURES

| Features                                        | Description                                                            |
|:------------------------------------------------|:-----------------------------------------------------------------------|
| **Kotlin**                                      | This app is completely written in Kotlin.                              |
| **MVVM architecture**                           | Using the lifecycle aware viewmodels, the view observes changes in the model / repository.|
| **Android Architecture Components**             | Lifecycle awareness has been achieved using a combination of LiveData, ViewModels and Room.|
| **Backend**                                    | Used Firebase products for backend and REST API|
| **Dependency Injection**                        | Common elements like context, networking interface are injected using Koin.|
| **Offline first architecture**                  | All the data is first tried to be loaded from the db and then updated from the server. This ensures that the app is usable even in an offline mode.|
| **Effective Networking**                        | Using a combination of Retrofit, Room and LiveData, we are able to handle networking in the most effective way.|
| **Intelligent sync**                            | Intelligent hybrid syncing logic makes sure your Android app does not make repeated calls to the same back-end API for the same data in a particular time period.|
| **Feature based packaging**                     | This screen-wise / feature-wise packaging makes code really easy to read and debug.|


## 📦 DOWNLOAD APK

You can download the apk from: 

1. [Google Playstore](https://play.google.com/store/apps/details?id=info.tumur.resume.app)
2. [Github Release](https://github.com/tumurb/Personal-Resume-Android/blob/master/app/release/Resume%201.2.apk)


## 📝 REQUIREMENTS

* JDK Version 1.7 & above
* [Android SDK.](http://developer.android.com/sdk/index.html)
* Android SDK Tools
* Android SDK Build tools 28
* Android Support Repository
* Android Support library

## 🌐 BACKEND
I used Firebase Cloud Storage as a backend server and Firebase Cloud Hosting and Functions for API.
You can find the Firebase Cloud Functions code in TypeScript here.

## 🔥 FIREBASE SETUP

This project uses Firebases Analytics and Crashlytics, Cloud Firestore, Remote Config, etc. You will need to generate the configuration file (`google-services.json`) and copy it to your `/app` dir. See links below

1. [Setup Firebase setup](https://firebase.google.com/docs/android/setup)
2. [Setup Firebase Crashlytics](https://firebase.google.com/docs/crashlytics/get-started/)
3. [Setup Firebase Cloud Firestore](https://firebase.google.com/docs/firestore/quickstart/)
4. [Setup Firebase Storage](https://firebase.google.com/docs/storage/android/start)
5. [Setup Firebase Cloud Functions](https://firebase.google.com/docs/functions/get-started)
6. [Setup Firebase Hosting](https://firebase.google.com/docs/hosting/quickstart)
7. [Setup Firebase Peformance Monitoring](https://firebase.google.com/docs/perf-mon/get-started-android)
8. [Setup Firebase Test Lab ](https://firebase.google.com/docs/test-lab/)
9. [Setup Firebase App Indexing ](https://firebase.google.com/docs/app-indexing/android/app)


## ⚙️ PROJECT SETUP

This project is built with Gradle, the [Android Gradle plugin](http://tools.android.com/tech-docs/new-build-system/user-guide) Clone this repository inside your working folder. Import the `settings.gradle` file in the root folder into e.g. Android Studio. (You can also have a look at the `build.gradle` files on how the projects depend on another.)

* Start Android Studio
* Select "Open Project" and select the generated root Project folder
* You may be prompted with "Unlinked gradle project" -> Select "Import gradle project" and select
the option to use the gradle wrapper
* You may also be prompted to change to the appropriate SDK folder for your local machine
* Once the project has compiled -> run the project!


## 🤝 CONTRIBUTING

### Would you like to contribute code?
1. [Fork Portfolio](https://github.com/tumurb/Portfolio/).
2. Create a new branch ([using GitHub](https://help.github.com/articles/creating-and-deleting-branches-within-your-repository/)) or the command `git checkout -b branch-name develop`).
3. [Start a pull request](https://github.com/tumurb/Portfolio/compare). Reference [existing issues](https://github.com/tumurb/Portfolio/issues) when possible.

### No code!
* You can [discuss a bug](https://github.com/tumurb/Bio/issues) or if it was not reported yet [submit a bug](https://github.com/tumurb/Bio/issues/new).


## 🚀 ANDROID JETPACK COMPONENTS

| Android Foundation | Architecture Components | Behavior | UI |
|:-------------------|:------------------------|:---------|:---------|
|[Appcompat](https://bit.ly/2NuMMK8)|[Data Binding](https://bit.ly/2GIx8KE)|[Media & Playback](https://bit.ly/2IAx3tY)|[Animations and Transitions](https://bit.ly/2N029tx)|
|[Android KTX](https://bit.ly/2EtrEB6)|[LiveData](https://bit.ly/2IAnewp)|[Notifications](https://bit.ly/2BQyjmZ)|[Emoji](https://bit.ly/2GZqSgQ)|
|[Multidex](https://bit.ly/2qnmlL1)|[Lifecycles](https://bit.ly/2E7ackq)|[Permissions](https://bit.ly/2x4HKiW)|[Constraint Layout](https://bit.ly/2tBwOVu)|
|[Test](https://bit.ly/2GIIOgo)|[Navigation](https://bit.ly/2NLl1MC)|[Preferences](https://bit.ly/2TcvKWl)|[Motion Layout](https://bit.ly/2Nsdsec)|
||[Paging](https://bit.ly/2IAnVWx)|[Sharing](https://bit.ly/2N6AhTX)|[Fragment](https://bit.ly/2LKTfPd)|
||[Room](https://bit.ly/2lXfwOX)|[Slices](https://bit.ly/2tALpjL)|[View Pager](https://bit.ly/2ThCMJm)|
||[ViewModel](https://bit.ly/2H0vRh3)||[Material Theming](https://bit.ly/2NpMeVH)|
||[Work Manager](https://bit.ly/2EtEaAm)||[Material Design Guideline](https://bit.ly/2VdJ6io)|
||||[Android Accessibility Guideline](https://bit.ly/2AfIFvN)|
||||[Google Play Instant App](https://bit.ly/2OEkwVy)|

## 📦 THIRD PARTY LIBRARIES

| Third party libraries | Firebase | Design tools |
|:-------------------|:------------------------|:------------------------|
|[Koin](https://bit.ly/2GIqyDE)|[Cloud Firestore](https://bit.ly/2U1Z9iZ)|[Sketch](https://bit.ly/23L9Cj0)|
|[Kotlin Coroutines](https://bit.ly/2wTru3E)|[Cloud Functions](https://bit.ly/2TfAkTE)|[Shape Shifter](https://bit.ly/2IuQzrQ)|
|[Retrofit](https://bit.ly/2pSxHbb)|[Hosting](https://bit.ly/2H5qbSW)|[Adobe Photoshop](https://adobe.ly/1Z5LVOp)|
|[OkHttp](https://bit.ly/2fVF6OA)|[Cloud Storage](https://bit.ly/2EuLtb6)|[Adobe Illustrator](https://adobe.ly/1G6wY8i)|
|[Glide](https://bit.ly/1RIHiKz)|[Crashlytics](https://bit.ly/2GIM9fq)|[Adobe After Effects](https://adobe.ly/1B8kNrh)|
|[Leak Canary](https://bit.ly/1Mz1RV4)|[Performance Monitoring](https://bit.ly/2Es0b2s)||
|[Timber](https://bit.ly/1zQNWqd)|[Test Lab](https://bit.ly/2C1h3Mb)||
|[ThreenTenABP](https://bit.ly/2GIykxt)|[App Indexing](https://bit.ly/2Tcy9jP)||
|[Stetho](https://bit.ly/2eZ3YI7)|||
|[Chrome Custom Tab](http://bit.ly/2XQsjYy)|||
|[CircleImageView](https://bit.ly/1MlTaA6)|||
|[SDP - Scalable Size Unit](https://bit.ly/2T9wlYZ)|||
|[Gradle Versions Plugin](https://bit.ly/1FtgBgA)|||
|[Moshi](https://bit.ly/2TLu7za)|||
|[InkPageIndicator](http://bit.ly/2CKlcnW)|||
|[Android Youtube Player](http://bit.ly/2JGdO0E)|||

## 📚 RESSOURCES

<em>A special thanks to the authors who shared following posts, they were a great resource during my learning 🙌🏻</em>


### Android & Kotlin
* <a href="http://bit.ly/2PFfPeS" target="_blank">Kotlin Bootcamp for Programmers by Google</a> 🔥
* <a href="http://bit.ly/2USby97" target="_blank">Kotlin for Android Developers</a> 🔥
* <a href="http://bit.ly/2ZTBbK8" target="_blank">Developing Android Apps with Kotlin by Google</a> 🔥🔥🔥
* <a href="https://developer.android.com/guide" target="_blank">Android Documentation & Guides | All in one Place</a> 🔥🔥🔥
* <a href="https://bit.ly/2Stik3R" target="_blank">Kotlin Null Safety Best Practices</a>
* <a href="https://youtu.be/AiFBEH54Xpw" target="_blank">Scope Functions in Kotlin - let run apply also with</a>

### Architecture
* <a href="http://bit.ly/2Gm8WuJ" target="_blank">Android Architecture: A Journey Looking For The Perfect Design</a>
* <a href="https://youtu.be/cpLUVOx-4u8" target="_blank">Best practices for a modularized app</a> 🔥

### Navigation Controller: Single Activity
* <a href="http://bit.ly/2GjyR6o" target="_blank">Principles of navigation</a>
* <a href="http://bit.ly/2DmD5JF" target="_blank">Update UI components with NavigationUI</a>
* <a href="http://bit.ly/2ZkepLl" target="_blank">Migrate to the Navigation component</a>
* <a href="http://bit.ly/2JmrvDM" target="_blank">Android Jetpack: manage UI navigation with Navigation Controller (Google I/O '18)</a>
* <a href="https://bit.ly/2Sz8mOu" target="_blank">Single Activity: Why, When, and How (Android Dev Summit '18)</a>
* <a href="http://bit.ly/2TCJHhh" target="_blank">Advanced Navigation Sample</a>
* <a href="http://bit.ly/2u6JFim" target="_blank">Master-Detail views with Navigation Components</a>
* <a href="http://bit.ly/2ZHYCWU" target="_blank">Scroll your Bottom Navigation View away with 10 lines of code</a>
* <a href="http://bit.ly/2FB4dWJ" target="_blank">Jetpack Navigation (Google I/O'19)</a> 🔥
* <a href="http://bit.ly/2Y4x5gb" target="_blank">Add Shared Element Transitions between destinations</a> 🔥

### Live Data & ViewModel
* <a href="https://bit.ly/2To47cV" target="_blank">Fun with LiveData (Android Dev Summit '18)</a>
* <a href="https://bit.ly/2Iy0psU" target="_blank">ViewModels and LiveData: Patterns + AntiPatterns</a>
* <a href="https://bit.ly/2QslbgB" target="_blank">LiveData with SnackBar, Navigation and other events</a>
* <a href="https://bit.ly/2H133oY" target="_blank">LiveData beyond the ViewModel</a>
* <a href="http://bit.ly/2HmgTm0" target="_blank">LiveData with single events</a>
* <a href="http://bit.ly/2HlOhtY" target="_blank">An Early Look at ViewModel SavedState</a>
* <a href="http://bit.ly/2KP8MBx" target="_blank">Locale changes and the AndroidViewModel antipattern</a>
* <a href="http://bit.ly/2xpufYx" target="_blank">ViewModels with Saved State, Jetpack Navigation, Data Binding and Coroutines</a> 🔥🔥🔥
* <a href="http://bit.ly/302cv1v" target="_blank">Use Kotlin coroutines with Architecture components</a> 🔥🔥🔥

### Room
* <a href="https://bit.ly/2EjIbGp" target="_blank">The Room in the House (Android Dev Summit '18)</a>
* <a href="https://bit.ly/2VskVgi" target="_blank">Course: Storing Data in Android with Room(Caster.io)</a> 🔥🔥🔥
* <a href="https://bit.ly/2E8o51R" target="_blank">7 Pro-tips for Room</a> 🔥🔥🔥
* <a href="https://bit.ly/2VhVU7g" target="_blank">Room 🔗 Coroutines</a> 🔥🔥🔥
* <a href="http://bit.ly/2XuIHtW" target="_blank">Populating Room Database with WorkManager sample app</a>
* <a href="http://bit.ly/2NA5ShV" target="_blank">Room Persistence Library with Coroutines</a>
* <a href="http://bit.ly/2EFtKhd" target="_blank">Dependency Injection with KOIN to Androidx Jetpack</a>
* <a href="http://bit.ly/2VZWOWp" target="_blank">Upgrade to Room 2.1.0-alpha05, use withTransaction</a>
* <a href="http://bit.ly/2Gosajl" target="_blank">Defining data using Room entities</a>
* <a href="http://bit.ly/2NdnpzV" target="_blank">Android Room Library Relations</a>
* <a href="http://bit.ly/2IQEH1G" target="_blank">Android Room with nested relationships</a>
* <a href="http://bit.ly/2Lnj2Qe" target="_blank">Room Persistence Library Part 2: Room Relationships</a>

### DataBinding
* <a href="https://bit.ly/2ISEooO" target="_blank">Modern Data Binding(Droidcon Italy 2018)</a>
* <a href="https://bit.ly/2EBBP6s" target="_blank">Level Up with Data Binding</a>
* <a href="https://bit.ly/2SmwyUc" target="_blank">Data Binding — Lessons Learnt</a>
* <a href="https://bit.ly/2UepFpM" target="_blank">Databinding in Kotlin World (Video)</a>
* <a href="https://bit.ly/2BWoSCI" target="_blank">Databinding in Kotlin World (Slides)</a>
* <a href="https://bit.ly/2VllLLQ" target="_blank">Leveraging Android Data Binding with Kotlin</a>
* <a href="http://bit.ly/2SMq8he" target="_blank">Simplifying UI States with Kotlin Sealed Classes and Data Binding</a>
* <a href="https://youtu.be/Iix75JjIbyI" target="_blank">Advanced Data Binding</a>

### Constraintlayouts & Motionlayout
* <a href="http://bit.ly/2HRtAWJ" target="_blank">Introduction to MotionLayout (part I)</a> 🔥
* <a href="http://bit.ly/2Uo9WHP" target="_blank">Introduction to MotionLayout (part II)</a> 🔥
* <a href="http://bit.ly/2YMRY1c" target="_blank">Introduction to MotionLayout (part III)</a> 🔥
* <a href="http://bit.ly/2FEjDsr" target="_blank">Introduction to MotionLayout (part IV)</a> 🔥
* <a href="http://bit.ly/2IRbi7q" target="_blank">What's New in ConstraintLayout (Google I/O'19)</a> 🔥
* <a href="https://bit.ly/2IJbBmD" target="_blank">ConstraintLayout Deep Dive (Android Dev Summit '18)</a>
* <a href="https://bit.ly/2HybEj5" target="_blank">MotionLayout & ConstraintLayout 2.0 (Droidcon SF 2018)</a>
* <a href="https://bit.ly/2HdY5Fx" target="_blank">Deep dive into MotionLayout (DroidKaigi 2019)</a>
* <a href="https://bit.ly/2Sx8Cxl" target="_blank">MotionLayout / Constraint Layout Samples</a>
* <a href="http://bit.ly/2IyzWLl" target="_blank">Pull the KeyTrigger with MotionLayout</a>
* <a href="https://bit.ly/2tRcGPj" target="_blank">Android Fundamentals: ConstraintLayout(Pluralsight)</a> 🔥
* <a href="https://bit.ly/2BXPQtE" target="_blank">Android Motion Layout tutorial – Collapsing view</a>
* <a href="http://bit.ly/2TMqkBx" target="_blank">MotionLayout Tutorial For Android: Getting Started</a> 🔥
* <a href="http://bit.ly/2ULnsRO" target="_blank">Say goodbye to LinearLayout with Flow</a>
* <a href="http://bit.ly/2DG8DdL" target="_blank">Exploring MotionLayout: Touch Regions</a>
* <a href="http://bit.ly/2Y19ZaR" target="_blank">ConstraintLayout 2.0</a>
* <a href="http://bit.ly/2VIjChe" target="_blank">ConstraintLayout 2.0.0 alpha 5</a>

### Koin
* <a href="https://bit.ly/2E7w3YP" target="_blank">Painless Android testing with Room & Koin</a>
* <a href="https://bit.ly/2Nrr1e7" target="_blank">Unlock your Android ViewModel power with Koin</a>
* <a href="https://bit.ly/2Vsvlwe" target="_blank">Course: Koin(Caster.io)</a> 🔥🔥🔥

### Kotlin Coroutines
* <a href="http://bit.ly/2Lec0yT" target="_blank">Coroutines on Android (part I): Getting the background</a> 🔥🔥🔥
* <a href="http://bit.ly/2DM0VPe" target="_blank">Coroutines on Android (part II): Getting the background</a> 🔥🔥🔥
* <a href="http://bit.ly/2YfQA6x" target="_blank">Coroutines on Android (part III): Getting the background</a> 🔥🔥🔥
* <a href="https://bit.ly/2TpQ06Q" target="_blank">Android Suspenders (Android Dev Summit '18)</a>
* <a href="https://bit.ly/2UdlyKy" target="_blank">Kotlin Coroutines: Beyond async/await (Droidcon NYC 2018)</a>
* <a href="https://bit.ly/2XlDiW8" target="_blank">Android Coroutine Recipes</a>
* <a href="https://bit.ly/2SrVt8Y" target="_blank">Kotlin Coroutines patterns & anti-patterns</a>
* <a href="https://bit.ly/2SoioSe" target="_blank">Android Networking in 2019 — Retrofit with Kotlin’s Coroutines</a>
* <a href="https://bit.ly/2EeC7Pg" target="_blank">The reason to avoid GlobalScope</a>
* <a href="https://bit.ly/2BZIs0G" target="_blank">Launching a Kotlin Coroutine for immediate execution on the Main thread</a>
* <a href="http://bit.ly/2tPu7zB" target="_blank">Coroutine Support in ViewModels</a>
* <a href="http://bit.ly/2Ulwghe" target="_blank">Managing exceptions in nested coroutine scopes</a>
* <a href="http://bit.ly/2PhfuPh" target="_blank">Cold flows, hot channels</a>
* <a href="http://bit.ly/2NdFPjT" target="_blank">Understand Kotlin Coroutines on Android (Google I/O'19)</a> 🔥🔥🔥

### WorkManager
* <a href="http://bit.ly/2EkJyEF" target="_blank">Working with WorkManager (Android Dev Summit '18)</a>
* <a href="http://bit.ly/2C7zRcw" target="_blank">Introducing WorkManager</a>
* <a href="http://bit.ly/2EOKlxQ" target="_blank">WorkManager Basics</a>
* <a href="http://bit.ly/2J8KuBA" target="_blank">Android Jetpack WorkManager Stable Release</a>
* <a href="http://bit.ly/2ZQPBeb" target="_blank">Working With WorkManager in Android Like A Pro</a>
* <a href="http://bit.ly/31OynPw" target="_blank">WorkManager meets Kotlin</a> 🔥
* <a href="http://bit.ly/31OyF94" target="_blank">WorkManager Periodicity</a> 🔥
* <a href="http://bit.ly/30IUxkW" target="_blank">Workout your tasks with WorkManager — Basics</a> 🔥

### Animation
* <a href="https://bit.ly/2VgM9WZ" target="_blank">Re-animation</a>
* <a href="https://bit.ly/2E3z1NW" target="_blank">Using vector assets in Android apps</a>
* <a href="https://bit.ly/2XjlQRW" target="_blank">Creating AnimatedVectorDrawables with Shape Shifter</a>
* <a href="https://bit.ly/2m8PVCy" target="_blank">Introduction to MotionLayout</a>
* <a href="https://bit.ly/2tAzToD" target="_blank">Defining motion paths in MotionLayout</a>
* <a href="http://bit.ly/2XyB74m" target="_blank">Motional Intelligence: Build Smarter Animations (Google I/O'19)</a> 🔥

### Paging
* <a href="https://bit.ly/2ElLa17" target="_blank">Android Jetpack: manage infinite lists with RecyclerView and Paging (Google I/O '18)</a> 🔥
* <a href="https://bit.ly/2ThdlHV" target="_blank">Playing with Paging Library, Retrofit, Coroutines, Koin & Testing.</a>
* <a href="http://bit.ly/2NylGBG" target="_blank">7 steps to implement Paging library in Android</a> 
* <a href="http://bit.ly/2tEZ0qq" target="_blank">Simple intro for Rest-Api loading of json data into recyclerview using Kotlin</a>
* <a href="http://bit.ly/2T2SU22" target="_blank">First Java MVVM project that includes: Paging library, ViewModel, LiveData, Room and Retrofit</a> 🔥
* <a href="http://bit.ly/2JFSJTY" target="_blank">Paging Library for Android With Kotlin: Creating Infinite Lists</a> 🔥
* <a href="http://bit.ly/2IIxaUC" target="_blank">The Movie Datbase</a>
* <a href="http://bit.ly/2F1E16p" target="_blank">Android RecyclerView Tutorial with Kotlin</a>
* <a href="https://youtu.be/8DPgwrV_9-g" target="_blank">Migrating to Paging library</a>
* <a href="http://bit.ly/2XDtwRW" target="_blank">Android Data Binding + ListAdapter</a>
* <a href="http://bit.ly/2KGTVsa" target="_blank">Android Data Binding + RecyclerView + Multiple View Type</a>
* <a href="http://bit.ly/2xiJ1zS" target="_blank">Android Data Binding for RecyclerView With LiveData (Kotlin)</a>
* <a href="http://bit.ly/2X94gDW" target="_blank">Android Paging Library with multiple view types</a> 

### ViewPager
* <a href="http://bit.ly/2CoFJhD" target="_blank">ViewPager Tutorial: Getting Started in Kotlin</a>
* <a href="http://bit.ly/2TM38n7" target="_blank">Exploring the View Pager 2</a>

### Android Accessibility
* <a href="https://bit.ly/2Vu4RL3" target="_blank">Course: Android App Development: Accessibility(LinkedIn Learning)</a> 🔥🔥🔥
* <a href="http://bit.ly/2H3QNUF" target="_blank">Making Android Accessibility Easy (Android Dev Summit '18)</a>
* <a href="http://bit.ly/2J2xYAk" target="_blank">Demystifying Android Accessibility Development (Google I/O'19)</a>

### Preferences
* <a href="http://bit.ly/2EnwVbQ" target="_blank">Preferential Practices for Preferences (Android Dev Summit '18)</a>
* <a href="http://bit.ly/2Ne4yVm" target="_blank">How to Code a Settings Screen in an Android App</a>

### Material Design & Theme
* <a href="http://bit.ly/2V7ZXGT" target="_blank">Material Design: Getting started</a>
* <a href="http://bit.ly/2Ej7Wqh" target="_blank">The Components of Material Design (Android Dev Summit '18)</a>
* <a href="http://bit.ly/2NAhzFb" target="_blank">Best Practices for Themes and Styles (Android Dev Summit '18)</a>
* <a href="http://bit.ly/2H5WXDD" target="_blank">Use Android Text Like a Pro (Android Dev Summit '18)</a>
* <a href="http://bit.ly/2JgPFj8" target="_blank">DayNight — Adding a dark theme to your app</a>
* <a href="http://bit.ly/2GlQaUn" target="_blank">Styles, Themes, Material Theming, Oh My!</a>
* <a href="http://bit.ly/2Gl4PiA" target="_blank">Seven best practices for inclusive product design</a>
* <a href="http://bit.ly/2Gwv2w6" target="_blank">WindowInsets — Listeners to layouts</a>
* <a href="https://youtu.be/92DL3-IQ_K0" target="_blank">Styles, Themes, Material Theming, Oh My!</a>
* <a href="http://bit.ly/2XPExfB" target="_blank">Playing with Material Design Transitions</a>
* <a href="http://bit.ly/2GPSJQ2" target="_blank">Mastering the Coordinator Layout</a>
* <a href="http://bit.ly/2J0Daq8" target="_blank">Integrate CoordinatorLayout + BottomNavigationView + Toolbar + TabLayout + ViewPager + Fragment + DrawerLayout</a>
* <a href="http://bit.ly/2PB0JqM" target="_blank">Google+ Android Sample App demo</a>
* <a href="http://bit.ly/2DFdagB" target="_blank">Android Design — Collapsing Toolbar: ScrollFlags Illustrated</a>
* <a href="http://bit.ly/2WfmVJt" target="_blank">AppBarLayout scroll behavior with layout_scrollFlags</a>
* <a href="http://bit.ly/2XXiHH6" target="_blank">Hands-on with Material Components for Android: Bottom Sheets</a>
* <a href="http://bit.ly/2PB6Kni" target="_blank">Full-screen BottomSheetDialog DIY</a>
* <a href="http://bit.ly/2PGo3Ub" target="_blank">Hands-on with Material Components for Android: Buttons</a>
* <a href="http://bit.ly/2DCT5ra" target="_blank">Android P — What’s New in Material Design</a>
* <a href="http://bit.ly/2KEbl8H" target="_blank">Dark Theme & Gesture Navigation (Google I/O'19)</a> 🔥

### Sample Data
* <a href="http://bit.ly/2FfK7kS" target="_blank">What's new with ConstraintLayout and Android Studio design tools</a>
* <a href="http://bit.ly/2O4z28Y" target="_blank">Tool Time – Part 1</a>
* <a href="http://bit.ly/2O8fu3z" target="_blank">Tool Time – Part 2</a>

### Instant App
* <a href="http://bit.ly/2Un8z8k" target="_blank">A simpler experience for instant apps</a>
* <a href="http://bit.ly/2GlM4f6" target="_blank">5 tips for using showInstallPrompt in your instant experience</a>

### Test
* <a href="http://bit.ly/2Dj9YXW" target="_blank">Leveling Up Your UI Tests With MockWebServer</a>

### Firebase
* <a href="http://bit.ly/2IHK8Ad" target="_blank">Building a “Serverless” RESTful API with Cloud Functions</a>
* <a href="http://bit.ly/2IHKs1T" target="_blank">Build a Serverless full stack app using firebase cloud functions</a>
* <a href="https://youtu.be/c93iGKyvh3o" target="_blank">How Firebase Cloud Functions support custom domains</a>
* <a href="http://bit.ly/2KT4A3Z" target="_blank">Serve dynamic content and host microservices with Cloud Functions</a>

### Network
* <a href="http://bit.ly/2Zl30Lr" target="_blank">Making Android Networking Pretty with Kotlin Coroutines</a>
* <a href="http://bit.ly/2Goeq8k" target="_blank">Improving App Network Architecture With Retrofit And Kotlin Coroutine Call Adapter</a>
* <a href="http://bit.ly/2DozXgu" target="_blank">Sealed Classes Instead of Exceptions in Kotlin</a>
* <a href="http://bit.ly/2XWnIUE" target="_blank">Suspend what you’re doing: Retrofit has now Coroutines support!</a>

### Retrofit & OkHttp, Moshi, ThreeTenABP
* <a href="http://bit.ly/2UveUys" target="_blank">Android Networking with Coroutines and Retrofit</a>
* <a href="http://bit.ly/2PlhtSH" target="_blank">Handle Complex Network Call with Kotlin Coroutine + Retrofit 2</a>
* <a href="http://bit.ly/2GxfdVU" target="_blank">Retrofit Kotlin coroutines</a>
* <a href="http://bit.ly/2DodcZW" target="_blank">Kotlin Coroutines—Handling concurrency like a pro (Retrofit2+Coroutines)</a>
* <a href="http://bit.ly/2Vjj94w" target="_blank">Modern concurrency on Android with Kotlin</a>
* <a href="https://youtu.be/jOeofbFWbsA" target="_blank">Networking the Kotlin way: Retrofit + Coroutines</a>
* <a href="http://bit.ly/2SXOm8e" target="_blank">Migrate from GSON to Moshi in Android</a>
* <a href="http://bit.ly/2GAXgG4" target="_blank">Modern DateTimes on Android</a>

### Firebase
* <a href="http://bit.ly/2YBre39" target="_blank">Perform simple and compound queries in Cloud Firestore</a>
* <a href="http://bit.ly/30aPlGi" target="_blank">Firebase Auth REST API</a>
* <a href="http://bit.ly/2FXvLW9" target="_blank">Writing conditions for Cloud Firestore Security Rules</a>
* <a href="http://bit.ly/2xAjbYm" target="_blank">Five tips to secure your app (Firebase Summit 2018)</a>
* <a href="http://bit.ly/2LDq3MU" target="_blank">Firestore Security Rules</a>
* <a href="https://youtu.be/QEuu9X9L-MU" target="_blank">Introduction to Firebase security rules (Firecasts)</a> 🔥

### Other
* <a href="https://bit.ly/2H1f1yE" target="_blank">Kotlin + buildSrc for Better Gradle Dependency Management</a>
* <a href="http://bit.ly/2TPA0LH" target="_blank">Sharing Gradle Configuration in Multi-Module Android Projects</a>
* <a href="http://bit.ly/32FalXF" target="_blank">android-youtube-player, an open source alternative to the official YouTube Player API</a>
* <a href="http://bit.ly/2GkcaQo" target="_blank">Android RecyclerView adding Search Filter</a>
* <a href="http://bit.ly/2YeFPEQ" target="_blank">Responding to a Refresh Request</a>
* <a href="http://bit.ly/32Iq0po" target="_blank">Create swipe views with tabs</a>
* <a href="" target="_blank"></a>

## Project Maintained By

<img src="https://firebasestorage.googleapis.com/v0/b/portfolio-app-147b5.appspot.com/o/pro.png?alt=media&token=f10a1d13-5ffb-46ed-9460-1bdb966365fe" align="left" width="60" hspace="10" vspace="10">
<b>Tumur.B (Alex)</b><br/>
Android Developer
<br/>
<br/>
<a href="https://play.google.com/store/apps/dev?id=4872099625526337244"><img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/icon_google.png?alt=media&token=df311441-34fe-44dd-bf24-c5cbf8f4a6c9" width="60"></a> <a href="https://tumur.me"><img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/icon_web.png?alt=media&token=3854f445-8465-4ad3-8cd8-7aca2999964f" width="60"></a>
<a href="https://twitter.com/tumur_alex"><img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/icon_twitter.png?alt=media&token=0ada2552-9a35-4231-a502-3feab11d67c6" width="60"></a>
<a href="https://www.linkedin.com/in/tumur-alex/"><img src="https://firebasestorage.googleapis.com/v0/b/ioco-5c746.appspot.com/o/icon_linkedin.png?alt=media&token=3a0baa2c-8a47-4301-8bf6-11603f3bfb0a" width="60"></a>


## License

    Copyright 2019 Tumur.B (Alex)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

