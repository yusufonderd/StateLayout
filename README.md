# StateLayout

## GIF
<img src="https://github.com/yusufonderd/StateLayout/blob/master/art/sample.gif" width="360" height="720" />

## Usage
* Add state layout to outside of your main content
```xml
<com.yonder.statelayout.StateLayoutView
    android:id="@+id/stateLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

  <-- Your main content --> 

 </com.yonder.statelayout.StateLayoutView>

```

#### Code:
```kotlin
    stateLayout.setState(State.LOADING)
    stateLayout.setState(State.EMPTY)
    stateLayout.setState(State.ERROR) 
    stateLayout.setState(State.CONTENT)
```

* Customize if you need
```xml
 <com.yonder.statelayout.StateLayoutView
    android:id="@+id/errorStateLayoutView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:loadingGravity="top"
    app:errorDrawable="@drawable/ic_error_outline_24"
    app:textErrorButtonTryAgain="@string/title_btn_try_again"
    app:textErrorDetail="@string/title_error_detail"
    app:textErrorTitle="@string/title_error"
    app:textLoading= "@string/title_loading"
    app:textEmpty= "@string/title_empty"
    app:emptyIconDrawable= "@drawable/ic_empty_box_24"
    app:defaultMargin= "8">
```

## Setup
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
       implementation 'com.github.yusufonderd:StateLayout:0.1.1'
}
```
