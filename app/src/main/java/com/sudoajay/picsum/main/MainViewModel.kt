package com.sudoajay.picsum.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.gson.Gson
import com.sudoajay.picsum.BaseActivity
import com.sudoajay.picsum.R
import com.sudoajay.picsum.main.api.PicsumInterfaceBuilder
import com.sudoajay.picsum.main.model.Person
import com.sudoajay.picsum.main.proto.ProtoManager
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.internal.processedrootsentinel.codegen._com_sudoajay_picsum_MainApplication
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.properties.Delegates


class MainViewModel @Inject constructor(application: Application): ViewModel(){
    private var _application = application

    var protoManager: ProtoManager = ProtoManager(application)
    var getJsonConverter: String = application.getString(R.string.jacksonJson_text)
    var isDatabase:Boolean =false

    var hideProgress: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getDataFromProtoDatastore()
        loadHideProgress()

    }

    private fun getDataFromProtoDatastore(){
        viewModelScope.launch {
            protoManager.getDatabase()
                .collect {
                    // Update View with the latest favorite news
                    isDatabase = it
                }
            protoManager.getJsonConverter().collect {
                getJsonConverter = it
            }
            if (getJsonConverter.isEmpty()) {
                getJsonConverter = _application.getString(R.string.jacksonJson_text)
                protoManager.setJsonConverter(getJsonConverter)
            }
        }
    }

    private fun loadHideProgress() {
        hideProgress.value = false
    }
}