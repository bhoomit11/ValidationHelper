package com.master.validationhelper

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.master.validationhelper.CustomLogicValidation
import java.util.regex.Pattern


data class ValidationModel(
    val mEditText: EditText? = null,
    val mConfirmationEditText: EditText? = null,
    val mTextInputLayout: TextInputLayout? = null,
    val mConfirmationTextInputLayout: TextInputLayout? = null,
    val mLength: Int? = -1,
    val mPattern: Pattern? = null,
    val mErrMsg: String? = null,
    val mIsRequired: Boolean? = true,
    val customLogicValidation: CustomLogicValidation? = null
)

