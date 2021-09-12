package com.sudoajay.picsum.helper

import android.content.Context
import android.widget.Toast
import com.sudoajay.picsum.R

object Toaster {
    fun showToast(context: Context ,  message:String){
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

}