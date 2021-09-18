package com.sudoajay.picsum.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudoajay.picsum.main.proto.ProtoManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application): ViewModel() {
    private var _application = application
    var searchValue = ""

    var hideProgress: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadHideProgress()

    }

    private fun loadHideProgress() {
        hideProgress.value = false
    }
}