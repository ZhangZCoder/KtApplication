package com.kt.ktapplication.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kt.ktapplication.R
import com.kt.ktapplication.mvp.model.bean.HotBean
import com.kt.ktapplication.mvp.model.bean.VideoBean
import com.kt.ktapplication.ui.home.VideoDetailActivity
import com.kt.ktapplication.utils.ImageLoadUtils
import java.io.Serializable

/**
 * Created by zz on 2020/6/19.
 */
class RankAdapter(context: Context, hotBean: HotBean):RecyclerView.Adapter<RankAdapter.ViewHolder>(){
    var mContext:Context = context
    var mData = hotBean
    var inflater:LayoutInflater
    init {
        inflater = LayoutInflater.from(mContext)
    }

    class ViewHolder(viewItem: View):RecyclerView.ViewHolder(viewItem)
    {
        var tvPhoto:ImageView = viewItem.findViewById(R.id.ivPhoto)
        var tvTitle:TextView = viewItem.findViewById(R.id.tvTitle)
        var tvTime:TextView = viewItem.findViewById(R.id.tvTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater!!.inflate(R.layout.item_rank, parent, false))
    }

    override fun getItemCount(): Int {
        return mData.itemList.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ImageLoadUtils.disPlay(mContext,holder.tvPhoto,mData.itemList.get(position).data?.cover?.feed.toString())
        holder.tvTitle.text = mData.itemList.get(position).data?.title
        var category = mData.itemList?.get(position)?.data?.category
        var duration = mData.itemList?.get(position)?.data?.duration
        var minute =duration?.div(60)
        var second = duration?.minus((minute?.times(60)) as Long )
        var realMinute : String
        var realSecond : String
        if(minute!!<10){
            realMinute = "0"+minute
        }else{
            realMinute = minute.toString()
        }
        if(second!!<10){
            realSecond = "0"+second
        }else{
            realSecond = second.toString()
        }
        holder?.tvTime?.text = "$category / $realMinute'$realSecond''"
        var photo = mData.itemList.get(position)?.data?.cover?.feed
        var author = mData.itemList.get(position)?.data?.author
        var desc = mData.itemList.get(position)?.data?.description
        var playUrl = mData.itemList.get(position)?.data?.playUrl
        var blurred = mData.itemList.get(position)?.data?.cover?.blurred
        var collect = mData.itemList.get(position)?.data?.consumption?.collectionCount
        var share = mData.itemList.get(position)?.data?.consumption?.shareCount
        var reply = mData.itemList.get(position)?.data?.consumption?.replyCount
        var title = mData.itemList.get(position)?.data?.title
        var time = System.currentTimeMillis()
        var videoBean = VideoBean(photo,title,desc,duration,playUrl,category,blurred,collect ,share ,reply,time)
        holder.itemView.setOnClickListener {
            var intent:Intent = Intent(mContext, VideoDetailActivity::class.java)
            intent.putExtra("bean",videoBean as Serializable)
            mContext!!.startActivity(intent)
        }
    }
}