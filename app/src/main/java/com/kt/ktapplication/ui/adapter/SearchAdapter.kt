package com.kt.ktapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.kt.ktapplication.R

/**
 * Created by zz on 2020/6/15.
 */
class SearchAdapter(context: Context, data : MutableList<String>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    var context:Context
    var mData:MutableList<String>
    var inflater:LayoutInflater
    init {
        this.context= context
        this.mData= data
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(inflater.inflate(R.layout.item_search, parent,false), context)
    }

    override fun getItemCount(): Int {
        return if (mData == null){
            0
        }else{
            mData.size
        }
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.tvWord.text = mData[position]
        val params = holder?.tvWord?.layoutParams
        if (params is FlexboxLayoutManager.LayoutParams) {
            (holder?.tvWord?.layoutParams as FlexboxLayoutManager.LayoutParams).flexGrow = 1.0f
        }

    }

    class SearchViewHolder(itemView: View, context: Context): RecyclerView.ViewHolder(itemView) {
        var tvWord : TextView =  itemView?.findViewById(R.id.tvWord) as TextView

    }

}