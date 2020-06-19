package com.kt.ktapplication.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder
import com.kt.ktapplication.R
import com.kt.ktapplication.mvp.model.bean.HomeBean
import com.kt.ktapplication.mvp.model.bean.VideoBean
import com.kt.ktapplication.ui.home.VideoDetailActivity
import com.kt.ktapplication.utils.ImageLoadUtils
import java.io.Serializable

/**
 * Created by zz on 2020/5/7.
 */
class HomeAdapter(context: Context, list: MutableList<HomeBean.IssueListBean.ItemListBean>): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    var context: Context? = null
    var list: MutableList<HomeBean.IssueListBean.ItemListBean>? = null
    var inflater: LayoutInflater? = null
    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    class HomeViewHolder(itemView: View?,context: Context) : RecyclerView.ViewHolder(itemView!!) {
        var tv_detail : TextView?= null
        var tv_title : TextView? = null
        var tv_time : TextView? = null
        var iv_photo : ImageView? = null
        var iv_user : ImageView? = null
        init {
            tv_detail = itemView?.findViewById(R.id.tv_detail) as TextView?
            tv_title = itemView?.findViewById(R.id.tv_title) as TextView?
            iv_photo = itemView?.findViewById(R.id.iv_photo) as ImageView?
            iv_user =  itemView?.findViewById(R.id.iv_user) as ImageView?
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(inflater?.inflate(R.layout.item_home, parent, false), context!!)
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        var bean = list?.get(position)
        var title = bean?.data?.title
        var category = bean?.data?.category
        var minute = bean?.data?.duration?.div(60)
        var second = bean?.data?.duration?.minus(minute?.times(60) as Long)
        var realMinute : String
        var realSecond : String = ""
        var duration = bean?.data?.duration


        realMinute = if ( minute!! < 10){
            "0$minute"
        }else{
            minute.toString()
        }

        realSecond = if (second!! < 10){
            "0$second"
        }else{
            second.toString()
        }

        var photo = bean?.data?.cover?.feed
        var author = bean?.data?.author
        var desc = bean?.data?.description
        var playUrl = bean?.data?.playUrl
        var blurred = bean?.data?.cover?.blurred
        var collect = bean?.data?.consumption?.collectionCount
        var share = bean?.data?.consumption?.shareCount
        var reply = bean?.data?.consumption?.replyCount
        var time = System.currentTimeMillis()

        var videoBean = VideoBean(photo,title,desc,duration,playUrl,category,blurred,collect ,share ,reply,time)

        ImageLoadUtils.disPlay(context!!, holder.iv_photo!!, photo as String)
        holder.tv_title?.text = title
        holder.tv_detail?.text = "发布于 $category/$realMinute : $realSecond"

        if (author != null){
            ImageLoadUtils.disPlay(context!!, holder.iv_user!!, author.icon)
        }else{
            holder.iv_user?.visibility = View.GONE
        }
        holder.itemView.setOnClickListener(View.OnClickListener {
            var intent:Intent = Intent(context, VideoDetailActivity::class.java)
            intent.putExtra("bean",videoBean as Serializable)
            context!!.startActivity(intent)
        })
    }

}