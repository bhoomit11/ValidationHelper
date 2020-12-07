package com.master.validationhelper

import android.text.Editable
import android.text.TextWatcher
import android.view.animation.Animation
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.master.validationhelper.extension.showKeyBoard

class Validator(private val anim: Animation?) {

    /**
     * Pattern validator for text input layout
     */
    fun validateTextInputPattern(model: ValidationModel): Boolean {
        if(model.mIsRequired == false && model.mTextInputLayout?.editText?.text.toString().isBlank()){
            return true
        }
        val pattern = model.mPattern
        val matcher = pattern?.matcher(model.mTextInputLayout?.editText?.text.toString().trim())
        if (matcher?.matches() == false) {
            model.mTextInputLayout?.error = model.mErrMsg
            model.mTextInputLayout?.startAnimation(anim)
            model.mTextInputLayout?.editText?.requestFocusAndKeyboard()
            return false
        }
        return true
    }

    /**
     * Required field validator for text input layout
     */
    fun validateTextInputRequired(model: ValidationModel): Boolean {
        if (model.mTextInputLayout?.editText?.text.toString().isBlank()) {
            model.mTextInputLayout?.error = model.mErrMsg
            model.mTextInputLayout?.startAnimation(anim)
            model.mTextInputLayout?.editText?.requestFocusAndKeyboard()
            return false
        }
        return true
    }

    /**
     * Pattern validator for edit text
     */
    fun validateEditTextPattern(model: ValidationModel): Boolean {
        if(model.mIsRequired == false && model.mTextInputLayout?.editText?.text.toString().isBlank()){
            return true
        }
        val pattern = model.mPattern
        val matcher = pattern?.matcher(model.mEditText?.text.toString().trim())
        if (matcher?.matches() == false) {
            model.mEditText?.error = model.mErrMsg
            model.mEditText?.startAnimation(anim)
            model.mEditText?.requestFocusAndKeyboard()
            return false
        }
        return true
    }


    /**
     * Required field validator for edit text
     */
    fun validateEditTextRequired(model: ValidationModel): Boolean {
        if (model.mEditText?.text.toString().isBlank()) {
            model.mEditText?.error = model.mErrMsg
            model.mEditText?.startAnimation(anim)
            model.mEditText?.requestFocusAndKeyboard()
            return false
        }
        return true
    }

    /**
     * Confirm password validator for text input layout
     */
    fun validateTextInputConfirmPassword(model: ValidationModel): Boolean {
        if (model.mTextInputLayout?.editText?.text.toString().isNotBlank() && model.mTextInputLayout?.editText?.text.toString() != model.mConfirmationTextInputLayout?.editText?.text.toString()) {
            model.mConfirmationTextInputLayout?.error = model.mErrMsg
            model.mConfirmationTextInputLayout?.startAnimation(anim)
            model.mConfirmationTextInputLayout?.editText?.requestFocusAndKeyboard()

            return false
        }
        return true
    }

    /**
     * Confirm password validator for edit text
     */
    fun validateEditTextConfirmPassword(model: ValidationModel): Boolean {
        if (model.mEditText?.text.toString().isNotBlank() && model.mEditText?.text.toString() != model.mConfirmationEditText?.text.toString()) {
            model.mConfirmationEditText?.error = model.mErrMsg
            model.mConfirmationEditText?.startAnimation(anim)
            model.mConfirmationEditText?.requestFocusAndKeyboard()

            return false
        }
        return true
    }

    /**
     * Remove error on text change for text input layout
     */
    fun errorRemoveOnChange(textInputLayout: TextInputLayout?) {
        textInputLayout?.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().isBlank()) {
                    textInputLayout.error = null
                    textInputLayout.isErrorEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }


    /**
     * Remove error on text change for edit text
     */
    fun errorRemoveOnChange(editText: EditText?) {
        editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().isBlank()) {
                    editText.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    private fun EditText?.requestFocusAndKeyboard(){
        this?.requestFocus()
        this?.showKeyBoard()
    }
}

