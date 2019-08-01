package com.mabrouk.loading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import com.mabrouk.loaderlib.RetryCallBack
import com.mabrouk.loading.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var layoutBinding: ActivityMainBinding
    val calback:RetryCallBack = object : RetryCallBack{
        override fun onRetry() {
            showLoader.set(true)
        }
    }
    val  showLoader:ObservableBoolean = ObservableBoolean()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       layoutBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        layoutBinding.showloader=showLoader
        layoutBinding.callBack=calback

        button.setOnClickListener {
            showLoader.set(false)
        }
    }
}
