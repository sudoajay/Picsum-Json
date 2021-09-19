package com.sudoajay.picsum

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.sudoajay.picsum.main.proto.ProtoManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseActivity :AppCompatActivity() {
    @Inject
    lateinit var protoManager: ProtoManager
    var getJsonConverter: String = ""
    var getDatabase: String = ""
    var getImageLoader: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSystemDefaultOn()
        getDataFromProtoDatastore()
    }

    private fun setSystemDefaultOn() {
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        )
    }

    fun isSystemDefaultOn(): Boolean {
        return resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

    private fun getDataFromProtoDatastore() {
        lifecycleScope.launch {
            protoManager.dataStoreStatePreferences.data.collectLatest {
                getJsonConverter = it.jsonConverter
                getDatabase = it.database
                getImageLoader = it.imageLoader
                if (getJsonConverter == "" && getDatabase == "" && getImageLoader == "")
                    protoManager.setDefaultValue()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}