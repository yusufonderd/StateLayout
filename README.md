# StateLayout


## Usage
* Add state layout to outside of your main content
```xml
<com.yonder.statelayout.StateLayoutView
    android:id="@+id/errorStateLayoutView"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

  <-- Your main content --> 

 </com.yonder.statelayout.StateLayoutView>

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
       implementation 'com.github.yusufonderd:StateLayout:0.1.0'
}
```
