package com.harbourspace.unsplash.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harbourspace.unsplash.api.GetImageProvider
import com.harbourspace.unsplash.data.ImageDatas
import com.harbourspace.unsplash.data.sb.UnsplashResultt

private const val TAG = "ImageGetModel"

class ImageGetModel : ViewModel(), UnsplashResultt {
    private val _itemI = MutableLiveData<ImageDatas>()
    val itemI: LiveData<ImageDatas> = _itemI

    private val providerGetImage by lazy {
        GetImageProvider()
    }
    fun getImageById(id:String){
        providerGetImage.getImageById(id, this)
    }
    override fun onDataFetchedSuccess(image: ImageDatas) {
        Log.d(TAG, "onDataFetchedSuccess | Received ${image.id} images")
        _itemI.value = image
    }
    override fun onDataFetchedFailed() {
        Log.e(TAG, "onDataFetchedFailed | Unable to retrieve YourImage")
    }
}