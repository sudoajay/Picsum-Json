package com.sudoajay.picsum.main

import android.app.Application
import android.util.Log
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

                if (getJsonConverter == "" && getDatabase == "") {
                    Log.e(
                        TAG,
                        "indie: value change - json - $getJsonConverter    databsae - $getDatabase"
                    )
                    protoManager.setDefaultValue()
                }

                Log.e(
                    TAG,
                    "getDataFromProtoDatastore: value change - json - $getJsonConverter    databsae - $getDatabase"
                )
            }


        }
    }

    private fun loadHideProgress() {
        hideProgress.value = false
    }
}