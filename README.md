# Loading
this library for loading show loader view 


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
