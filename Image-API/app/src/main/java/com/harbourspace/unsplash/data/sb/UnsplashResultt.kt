package com.harbourspace.unsplash.data.sb

import com.harbourspace.unsplash.data.ImageDatas

interface UnsplashResultt {
    fun onDataFetchedSuccess(image : ImageDatas)

    fun onDataFetchedFailed()
}