package com.kt.ktapplication.mvp.model.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by zz on 2020/6/12.
 */
data class VideoBean (var feed:String?,var title:String?,var description:String?,
                      var duration: Long?,var playUrl: String?,var category: String?,
                      var blurred : String?,var collect:Int?,var share:Int?,var reply:Int?,var time:Long)  : Parcelable,
    Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readLong()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(feed)
        dest.writeString(title)
        dest.writeString(description)
        dest.writeValue(duration)
        dest.writeString(playUrl)
        dest.writeString(category)
        dest.writeString(blurred)
        dest.writeValue(collect)
        dest.writeValue(share)
        dest.writeValue(reply)
        dest.writeLong(time)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<VideoBean> {
        override fun createFromParcel(parcel: Parcel): VideoBean {
            return VideoBean(parcel)
        }

        override fun newArray(size: Int): Array<VideoBean?> {
            return arrayOfNulls(size)
        }
    }
}

