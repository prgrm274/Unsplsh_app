package com.programmer2704.unsplash.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    @Expose val id: String,
    @Expose val username: String,
    @Expose val name: String
) : Parcelable
