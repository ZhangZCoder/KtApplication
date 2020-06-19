package com.kt.ktapplication.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kt.ktapplication.R
import com.kt.ktapplication.mvp.model.bean.HotBean
import com.kt.ktapplication.utils.ImageLoadUtils
import java.text.SimpleDateFormat

/**
 * Created by zz on 2020/6/17.
 */
class SearchResultAdapter(context: Context, data :List<HotBean.ItemListBean>):RecyclerView.Adapter<SearchResultAdapter.ViewHolder>(){
    var inflater:LayoutInflater
    var mContext:Context
    var list:List<HotBean.ItemListBean>
    init {
        mContext = context
        list = data
        inflater = LayoutInflater.from(context)
    }

    class ViewHolder(itemView:View, context: Context) : RecyclerView.ViewHolder(itemView) {
        var ivImage:ImageView = itemView.findViewById(R.id.ivImage)
        var tvTitle:TextView = itemView.findViewById(R.id.tvTitle)
        var tvDetail:TextView = itemView.findViewById(R.id.tvDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_result, parent, false),mContext)
    }

    override fun getItemCount(): Int {
        return if (list == null){
            0
        }else{
            list.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var itemBean:HotBean.ItemListBean = list.get(position)
        holder.tvTitle.text = itemBean.data?.title
        var category = itemBean.data?.category
        var duration = itemBean.data?.duration
        var minute =duration?.div(60)
        var second = duration?.minus((minute?.times(60)) as Long )
        var releaseTime = itemBean.data?.releaseTime
        var smf : SimpleDateFormat = SimpleDateFormat("MM-dd")
        var date = smf.format(releaseTime)
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
        holder?.tvDetail?.text = "$category / $realMinute'$realSecond'' / $date"
        if(itemBean.data?.cover?.feed!=null){
            ImageLoadUtils.disPlay(mContext,holder.ivImage, itemBean.data?.cover?.feed!!)
        }
    }
}