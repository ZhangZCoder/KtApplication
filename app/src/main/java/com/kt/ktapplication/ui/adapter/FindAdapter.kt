package com.kt.ktapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kt.ktapplication.R
import com.kt.ktapplication.mvp.model.bean.FindBean
import com.kt.ktapplication.utils.ImageLoadUtils
import kotlinx.android.synthetic.main.item_find.view.*

/**
 * Created by zz on 2020/6/18.
 */
class FindAdapter(context: Context, beans:MutableList<FindBean>):RecyclerView.Adapter<FindAdapter.ViewHolder>(){
    var mContext:Context = context
    var mData:MutableList<FindBean> = beans
    var inflater:LayoutInflater = LayoutInflater.from(mContext)

    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPhoto:ImageView = itemView.ivPhoto
        var tvTitle:TextView = itemView.tvTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_find, parent, false)) 
    }

    override fun getItemCount(): Int {
        return if (mData == null){
            0
        }else{
            mData.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = mData.get(position).name
        ImageLoadUtils.disPlay(mContext, holder.ivPhoto, mData.get(position).bgPicture.toString())
    }
}