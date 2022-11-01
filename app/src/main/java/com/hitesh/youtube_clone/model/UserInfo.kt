package com.hitesh.youtube_clone.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "userinfo"
)
@Parcelize
data class UserInfo(
    var name: String? = null,
    var email: String? = null,
    var subscribers: Int? = null
): Parcelable
