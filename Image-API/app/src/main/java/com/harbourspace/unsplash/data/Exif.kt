package com.harbourspace.unsplash.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Exif(
    val aperture: String?,
    val exposure_time: String?,
    val focal_length: String?,
    val iso: Int?,
    val make: String?,
    val model: String?,
    val name: String?
) : Parcelable