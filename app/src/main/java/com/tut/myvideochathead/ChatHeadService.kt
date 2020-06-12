package com.tut.myvideochathead

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager


class ChatHeadService : Service() {

    var chatView: ChatHeadView? = null
    lateinit var windowManager: WindowManager
    lateinit var params:WindowManager.LayoutParams



    private fun isInRange(x: Float?, y: Float?): Boolean {
        return ((x ?: 300f) <= 300f || (y ?: 300f) <= 300f)
    }

    override fun onCreate() {
        super.onCreate()
        chatView = ChatHeadView(baseContext)
        chatView?.btnClose?.setOnClickListener {
            close()
        }
        chatView?.image?.setOnTouchListener(object :View.OnTouchListener{
            private var initialX = 0
            private var initialY = 0
            private var initialTouchX = 0f
            private var initialTouchY = 0f

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if(event == null) return false
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {

                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;

                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        var Xdiff = (event.getRawX() - initialTouchX)
                        var Ydiff = (event.getRawY() - initialTouchY)
                        return true
                    }
                    MotionEvent.ACTION_MOVE -> {

                        //Calculate the X and Y coordinates of the view.
                        params.x = (initialX + (event.getRawX() - initialTouchX)).toInt()
                        params.y = (initialY + (event.getRawY() - initialTouchY)).toInt()


                        //Update the layout with new X & Y coordinate
                        windowManager.updateViewLayout(chatView, params);

                        return true
                    }
                }
                return false;
            }

        })
//        chatView?.findViewById<ConstraintLayout>(R.id.root)
//            ?.setOnTouchListener(object : View.OnTouchListener {
//                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//
//                    val isInside = isInRange(event?.x, event?.y)
//                    Log.d("###", "#### touch $isInside")
//                    return isInside
//
//                }
//
//            })


        //Add the view to the window.

        val LAYOUT_FLAG: Int
        LAYOUT_FLAG = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }
        //Add the view to the window.
        params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            LAYOUT_FLAG,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE ,//or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,

            PixelFormat.TRANSLUCENT
        )

        //Specify the chat head position
//Initially view will be added to top-left corner

        //Specify the chat head position
//Initially view will be added to top-left corner
        params.gravity = Gravity.TOP or Gravity.LEFT
        params.x = 100
        params.y = 100


        //Add the view to the window

        //Add the view to the window
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.addView(chatView, params)



    }

    override fun onDestroy() {
        super.onDestroy()
        removeChatHead()
    }

    private fun removeChatHead(){
        chatView?.let {
            windowManager.removeView(it)
        }
        chatView = null
    }

    private fun close(){
        removeChatHead()
        stopSelf()
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}