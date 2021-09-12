package com.sudoajay.picsum.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudoajay.picsum.main.proto.ProtoManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(application: Application): ViewModel() {
    private var _application = application
    private var TAG = "MainViewModelTAG"

    var protoManager: ProtoManager = ProtoManager(application)
    var getJsonConverter: String = ""
    var getDatabase: String = ""
    var getImageLoader: String = ""
    var searchValue = ""

    var hideProgress: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getDataFromProtoDatastore()
        loadHideProgress()

    }

    private fun getDataFromProtoDatastore() {
        viewModelScope.launch {
            protoManager.dataStoreStatePreferences.data.collectLatest {
                getJsonConverter = it.jsonConverter
                getDatabase = it.database
                getImageLoader = it.imageLoader
                if (getJsonConverter == "" && getDatabase == "" && getImageLoader == "")
                    protoManager.setDefaultValue()
            }
        }
    }

    private fun loadHideProgress() {
        hideProgress.value = false
    }
}