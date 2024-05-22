package com.harbourspace.unsplash.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Location(
    val city: String?,
    val country: String?,
    val position: Position?
): Parcelable