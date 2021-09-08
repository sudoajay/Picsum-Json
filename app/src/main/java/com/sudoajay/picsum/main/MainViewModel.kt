package com.sudoajay.picsum.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudoajay.picsum.R
import com.sudoajay.picsum.main.proto.ProtoManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


class MainViewModel @Inject constructor(application: Application): ViewModel() {
    private var _application = application
    private var TAG = "MainViewModelTAG"

    var protoManager: ProtoManager = ProtoManager(application)
    var getJsonConverter: String = _application.getString(R.string.jacksonJson_text)
    var isDatabase: Boolean = false

    var hideProgress: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getDataFromProtoDatastore()
        loadHideProgress()

    }

    private fun getDataFromProtoDatastore(){
        viewModelScope.launch {
            protoManager.dataStoreStatePreferences.data.collectLatest {
                getJsonConverter = it.jsonConverter
                isDatabase = it.database
            }
        }
    }

    private fun loadHideProgress() {
        hideProgress.value = false
    }
}