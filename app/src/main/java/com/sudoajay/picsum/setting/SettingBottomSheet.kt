package com.sudoajay.picsum.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.LayoutSettingBottomSheetBinding
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.proto.ProtoManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingBottomSheet(var mainActivity: MainActivity) : BottomSheetDialogFragment() {
    lateinit var protoManager: ProtoManager
    private var  TAG:String = "SettingBottomSheetTag"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val myDrawerView = layoutInflater.inflate(R.layout.layout_setting_bottom_sheet, container, false)
        val binding = LayoutSettingBottomSheetBinding.inflate(
            layoutInflater,
            myDrawerView as ViewGroup,
            false
        )

        binding.bottomSheet = this
        binding.viewModel = mainActivity.viewModel
        binding.lifecycleOwner = this
        protoManager = mainActivity.viewModel.protoManager

        return binding.root
    }


    fun setJsonValue(json: String) {
        lifecycleScope.launch {
            protoManager.setJsonConverter(json)
        }
        mainActivity.viewModel.getJsonConverter = json
        dismiss()
    }

    fun setValue(boolean: Boolean) {
        lifecycleScope.launch {
            protoManager.setDatabase(boolean)
        }
        mainActivity.viewModel.isDatabase = boolean

        dismiss()
    }


}