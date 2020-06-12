package com.tut.myvideochathead

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class ChatHeadView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var root:View
    lateinit var image:ImageView
    lateinit var btnClose:Button
    init {
        init(attrs)
    }

//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        return true
//    }

//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        Log.d("###","###onTouchEvent")
//        super.onTouchEvent(event)
//        return true//super.onTouchEvent(event)
//    }
//
//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//
//       // parent.requestDisallowInterceptTouchEvent(true)
//        val res = super.dispatchTouchEvent(ev)
//        Log.d("###","###dispatchTouchEvent $res")
//        return  true
//    }
//
//    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        Log.d("###","###onInterceptTouchEvent")
//        //super.onInterceptTouchEvent(ev)
//        return true//super.onInterceptTouchEvent(ev)
//    }


    private fun init(attrs: AttributeSet?) {
        root = View.inflate(context, R.layout.custom_layout, this)
        this.setOnClickListener {
            Log.d("###", "### on click 1... ")
        }
        root.setOnClickListener {
            Log.d("###", "### on click .2.. ")
        }



        btnClose = root.findViewById<Button>(R.id.btnClose)
        image = root.findViewById<ImageView>(R.id.imageFace)

//        image.setOnTouchListener(object : OnTouchListener{
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                Log.d("###", "### on touch ... ")
//                return true
//            }
//
//        })

//        btnClose.setOnClickListener {
//            Log.d("###", "### on click .3.. ")
//        }
//
//        image.setOnClickListener {
//            Log.d("###", "### on click .4.. ")
//        }

//        val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
//        try {
//            val text = ta.getString(R.styleable.CustomView_text)
//            val drawableId = ta.getResourceId(R.styleable.CustomView_image, 0)
//            if (drawableId != 0) {
//                val drawable = AppCompatResources.getDrawable(context, drawableId)
//                image_thumb.setImageDrawable(drawable)
//            }
//            text_title.text = text
//        } finally {
//            ta.recycle()
//        }
    }
}