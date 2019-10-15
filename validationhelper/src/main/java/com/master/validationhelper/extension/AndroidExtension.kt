package com.master.validationhelper.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


fun View.showKeyBoard() {
    /*this.postDelayed({
        val inputManager = this.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(this, InputMethodManager.HIDE_NOT_ALWAYS)
    }, 100)*/

    val inputManager = this.context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.showSoftInput(this, 0)
}
