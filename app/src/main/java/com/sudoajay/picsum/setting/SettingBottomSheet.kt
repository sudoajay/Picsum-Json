package com.sudoajay.picsum.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.LayoutSettingBottomSheetBinding

class SettingBottomSheet : BottomSheetDialogFragment() {

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
        binding.lifecycleOwner = this


        return binding.root
    }


    fun setValue() {

    }



}