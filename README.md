[![](https://jitpack.io/v/mohamedmabrouk582/Loading.svg)](https://jitpack.io/#mohamedmabrouk582/Loading)

# Loading
this library for loading show loader view 

<p align="center"><img src="https://github.com/mohamedmabrouk582/Loading/blob/master/screans/demo.gif"></p>


## Adding LoaderLib to your project
Include the following dependencies in your app's build.gradle :

allprojects {
repositories {
      maven { url 'https://jitpack.io' }
}
	}
```
dependencies {
  implementation 'com.github.mohamedmabrouk582:Loading:$last_version'
}




<com.mabrouk.loaderlib.LoaderView
            android:id="@+id/loader_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:showLoader="true"
            app:loaderType="WanderingCubes"
            app:showError="false"
            app:callBack="@{callBack}"
            app:loaderColor="@color/colorAccent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>
	    ```
