package com.dienty.core.structure.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.dienty.core.structure.R

class LoadingDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.view_loading_dialog)

        // Make the background transparent
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Prevent dialog from being canceled by touching outside or pressing back
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}