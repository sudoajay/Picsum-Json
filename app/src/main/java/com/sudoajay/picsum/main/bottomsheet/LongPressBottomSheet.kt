package com.sudoajay.picsum.main.bottomsheet

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.LayoutLongPressBottomSheetBindingImpl


class LongPressBottomSheet(var openUrl: String, var downloadUrl: String) :
    BottomSheetDialogFragment() {


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

    fun viewUrl() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(openUrl))
        startActivity(browserIntent)
        dismiss()
    }

    fun downloadUrl() {
        val downloadManager =
            requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(downloadUrl)
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        downloadManager.enqueue(request)
        Toast.makeText(
            requireContext(), "Downloaded",
            Toast.LENGTH_SHORT
        ).show()
        dismiss()
    }


}

