package com.kt.ktapplication.ui.home

import android.graphics.BitmapFactory
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kt.ktapplication.R
import com.kt.ktapplication.mvp.model.bean.VideoBean
import com.kt.ktapplication.utils.ImageLoadUtils
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import kotlinx.android.synthetic.main.activity_video_detail.*
import java.io.FileInputStream
import java.util.*

class VideoDetailActivity : AppCompatActivity() {

    lateinit var videoBean:VideoBean
    lateinit var orientationUtils: OrientationUtils
    companion object{
        var MSG_IMAGE_LOADED = 101
    }
    lateinit var imageView: ImageView

    var mHandler:Handler = object:Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                MSG_IMAGE_LOADED ->
                    gsyPlayer.setThumbImageView(imageView)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_detail)
        videoBean = intent.getParcelableExtra<VideoBean>("bean")
        prepareVideo()
    }

    private fun prepareVideo(){
        ImageLoadUtils.displayHigh(this, ivBackground, videoBean.blurred!!)
        tvQuestion.text = videoBean.title
        tvTime.text = videoBean.description
        tvDescribe.text = videoBean.description
        var duration = videoBean.duration
        var category = videoBean.category
        var minute = duration?.div(60)
        var second = duration?.minus((minute?.times(60)) as Long)
        var realMinute: String
        var realSecond: String
        if (minute!! < 10) {
            realMinute = "0" + minute
        } else {
            realMinute = minute.toString()
        }
        if (second!! < 10) {
            realSecond = "0" + second
        } else {
            realSecond = second.toString()
        }
        tvTime.text = "$category / $realMinute'$realSecond''"
        gsyPlayer.setUp(videoBean.playUrl, false, null, null)
        imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        ImageViewAsyncTask(mHandler,this,imageView).execute(videoBean.feed)
        gsyPlayer.backButton.visibility = View.VISIBLE
        gsyPlayer.backButton.setOnClickListener(View.OnClickListener {
            finish()
        })
        tvShare.text = videoBean.share.toString()
        tvFavor.text = videoBean.collect.toString()
        tvReply.text = videoBean.reply.toString()


    }


    private class ImageViewAsyncTask(handler: Handler, activity: VideoDetailActivity, private val imageView: ImageView): AsyncTask<String, Void, String>(){
        var mActivity = activity
        private var handler = handler
        private var mIs: FileInputStream? = null

        override fun doInBackground(vararg params: String?): String {
//            TODO("Not yet implemented")
            val future = Glide.with(mActivity)
                .load(params[0])
                .downloadOnly(100, 100)
            var cachFile = future.get()
            var path:String = cachFile.absolutePath

            return path
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            mIs = FileInputStream(result)
            val bitmap = BitmapFactory.decodeStream(mIs)
            imageView.setImageBitmap(bitmap)
            var message = handler.obtainMessage()
            message.what = MSG_IMAGE_LOADED
            handler.sendMessage(message)
        }
    }
}
