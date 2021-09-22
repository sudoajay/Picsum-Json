package com.sudoajay.picsum.main.bottomsheet

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.LayoutNavigationDrawerBottomSheetBinding
import com.sudoajay.picsum.helper.Toaster
import com.sudoajay.picsum.main.sendFeedback.SendFeedback
import javax.inject.Inject

import java.io.File
import androidx.test.core.app.ApplicationProvider.getApplicationContext





class NavigationDrawerBottomSheet @Inject constructor() : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val myDrawerView =
            layoutInflater.inflate(R.layout.layout_navigation_drawer_bottom_sheet, null)
        val binding = LayoutNavigationDrawerBottomSheetBinding.inflate(
            layoutInflater,
            myDrawerView as ViewGroup,
            false
        )
        binding.navigation = this

        return binding.root
    }

    private fun callToast() =
        Toaster.showToast(requireContext(), getString(R.string.workOnProgress_text))


    fun rateUs() = callToast()

    fun moreApp() = callToast()

    fun sendFeedback() =  startActivity(Intent(requireContext(), SendFeedback::class.java))

    fun shareApk() = shareApplication()


    fun developerPage() {
        val page = "https://github.com/SudoAjay"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(page)
        startActivity(i)
    }

    fun getVersionName(): String {
        var versionName = "1.0.0"
        try {
            versionName = requireContext().packageManager
                .getPackageInfo(requireContext().packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return "%s %s".format(getString(R.string.app_version_text), versionName)
    }




    private fun shareApplication() {
        val page = "https://github.com/SudoAjay"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(page)
        startActivity(i)
    }

}

