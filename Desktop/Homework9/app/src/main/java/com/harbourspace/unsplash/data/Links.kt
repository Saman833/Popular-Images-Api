package com.harbourspace.unsplash.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Links(
    val download: String?,
    val download_location: String?,
    val html: String?,
    val self: String?
) : Parcelable