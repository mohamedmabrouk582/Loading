package com.mabrouk.loaderlib

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.*
import kotlinx.android.synthetic.main.loader_layout.view.*


/*
* Created By mabrouk on 31/07/19
* Loading
*/

class LoaderView : ConstraintLayout{
   private  var showLoader:Boolean = false
   private  var showError:Boolean = false
   private  var enableRetry:Boolean = true
   private  var errorMsg:String = " No Data Found "
   private  var loaderColor:Int = Color.WHITE
   private  var loaderType:LoaderType=LoaderType.RotatingPlane
   private  var callBack:RetryCallBack?=null
   private  var defStyleAttr:Int=0
   val counDown:CountDownTimer by lazy { object :CountDownTimer(3000,1000){
       override fun onFinish() {
         start()
       }

       override fun onTick(p0: Long) {
          if (dots_txt.text.length>=3) dots_txt.text="" else dots_txt.append(".")
       }

   }
   }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init(context,attrs)
     }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
       this.defStyleAttr=defStyleAttr
        init(context,attrs)
    }

    fun init(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.loader_layout,this,true)
      val typedArray = context.obtainStyledAttributes(attrs,R.styleable.LoaderView,defStyleAttr,0)
      showLoader=typedArray.getBoolean(R.styleable.LoaderView_showLoader,false)
      showError=typedArray.getBoolean(R.styleable.LoaderView_showError,false)
      enableRetry=typedArray.getBoolean(R.styleable.LoaderView_enableRetry,true)
      typedArray.getString(R.styleable.LoaderView_errorMsg)?.apply { errorMsg=this }
      loaderColor=typedArray.getColor(R.styleable.LoaderView_loaderColor,Color.WHITE)
      loaderType=LoaderType.values()[typedArray.getInt(R.styleable.LoaderView_loaderType,0)]
      setUp()
      typedArray.recycle()
    }

    fun setUp(){
        if (showError) showError() else hideError()
        if (showLoader) showLoader() else hideLoader()
        callBack?.apply {
            retry_btn.setOnClickListener {
                onRetry()
            }
        }
        error_msg.text=errorMsg
        loaderType()
        setupColors()
    }

    fun showLoader(){
        loader_bar.visibility= VISIBLE
        loading_txt.visibility= VISIBLE
        dots_txt.visibility = VISIBLE
        counDown.start()
        hideError()
    }

    fun hideLoader(){
        counDown.onFinish()
        counDown.cancel()
        loader_bar.visibility= GONE
        loading_txt.visibility= GONE
        dots_txt.visibility = GONE
    }

    fun showError(){
        error_msg.visibility = VISIBLE
        if (enableRetry) enableRetry() else disableRetry()
        hideLoader()
    }

    fun hideError(){
        error_msg.visibility = GONE
        disableRetry()
    }
    fun enableRetry(){
        retry_btn.visibility = VISIBLE
    }

    fun disableRetry(){
        retry_btn.visibility = GONE
    }

    fun setUpLoader(sprite: Sprite){
        sprite.color=loaderColor
        loader_bar.setColor(loaderColor)
        loader_bar.setIndeterminateDrawable(sprite)
    }

    fun setupColors(){
        retry_btn.setTextColor(loaderColor)
        error_msg.setTextColor(loaderColor)
        loading_txt.setTextColor(loaderColor)
        dots_txt.setTextColor(loaderColor)
        val background = retry_btn.getBackground()
        background.setColorFilter(loaderColor, PorterDuff.Mode.SRC_ATOP)
    }

    fun loaderType(){
        setUpLoader(sprite = selectedLoaderType())
    }

    private fun selectedLoaderType() : Sprite{
        return when (loaderType) {
            LoaderType.RotatingPlane -> RotatingPlane()
            LoaderType.ChasingDots -> ChasingDots()
            LoaderType.Circle -> Circle()
            LoaderType.CubeGrid -> CubeGrid()
            LoaderType.DoubleBounce -> DoubleBounce()
            LoaderType.FadingCircle ->FadingCircle()
            LoaderType.FoldingCube ->FoldingCube()
            LoaderType.Pulse -> Pulse()
            LoaderType.RotatingCircle -> RotatingCircle()
            LoaderType.ThreeBounce -> ThreeBounce()
            LoaderType.WanderingCubes ->WanderingCubes()
            LoaderType.Wave -> Wave()
            LoaderType.MultiplePulse->MultiplePulse()
            LoaderType.MultiplePulseRing ->MultiplePulseRing()
            LoaderType.PulseRing -> PulseRing()
        }
    }

    fun setCallBack(callBack: RetryCallBack?){
        this.callBack=callBack
        callBack?.apply {
            retry_btn.setOnClickListener {
                onRetry()
            }
        }
    }

    fun setShowLoader(showLoader:Boolean){
        this.showLoader=showLoader
        if (showLoader) showLoader() else hideLoader()
    }

    fun setShowError(showError:Boolean){
        this.showError=showError
        if (showError) showError() else hideError()
    }

    fun setEnableRetry(enableRetry:Boolean){
        this.enableRetry=enableRetry
        if (enableRetry) enableRetry() else disableRetry()
    }

    fun setErrorMsg(errorMsgs:String?){
        errorMsgs?.apply {
            errorMsg = this
            error_msg.text = this
        }
    }



}