package com.sudoajay.picsum.main.bottomsheet

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.LayoutLongPressBottomSheetBindingImpl

class LongPressBottomSheet : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val myDrawerView =
            layoutInflater.inflate(R.layout.layout_long_press_bottom_sheet, null)
        val binding = LayoutLongPressBottomSheetBindingImpl.inflate(
            layoutInflater,
            myDrawerView as ViewGroup,
            false
        )
        binding.bottomSheet = this

        return binding.root
    }




}

