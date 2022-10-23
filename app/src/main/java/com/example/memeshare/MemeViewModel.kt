package com.example.memeshare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memeshare.network.MemeApi
import com.example.memeshare.network.MemePhoto
import kotlinx.coroutines.launch

enum class MemeApiStatus { LOADING, ERROR, DONE}


class MemeViewModel : ViewModel() {
    private val _status = MutableLiveData<MemeApiStatus>()
    val status: LiveData<MemeApiStatus> = _status

    private val _meme = MutableLiveData<MemePhoto>()
    val meme: LiveData<MemePhoto> = _meme
    init {
        getMemePhoto()
    }

      fun getMemePhoto() {
        viewModelScope.launch {
            _status.value = MemeApiStatus.LOADING
            try {
                val listResult = MemeApi.retrofitService.getMeme()
                _meme.value = listResult
                _status.value = MemeApiStatus.DONE
            } catch(e: Exception) {
                _status.value = MemeApiStatus.ERROR
//                _meme.value = MemePhoto("error", "error")
            }
        }
    }
}