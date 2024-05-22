package com.harbourspace.unsplash.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Tag(
    val title: String?
): Parcelable