package com.sudoajay.picsum.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudoajay.picsum.R
import com.sudoajay.picsum.main.proto.ProtoManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class MainViewModel @Inject constructor(application: Application): ViewModel() {
    private var _application = application
    @Inject

    lateinit var protoManager: ProtoManager
    var getJsonConverter: String = application.getString(R.string.jacksonJson_text)
    var isDatabase: Boolean = false

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