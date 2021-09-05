package com.sudoajay.picsum.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sudoajay.picsum.main.api.PicsumInterfaceBuilder
import com.sudoajay.picsum.main.model.Person
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class MainViewModel @Inject constructor(application: Application){

    var hideProgress: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadHideProgress()
    }

    private fun loadHideProgress() {
        hideProgress.value = false
    }
}