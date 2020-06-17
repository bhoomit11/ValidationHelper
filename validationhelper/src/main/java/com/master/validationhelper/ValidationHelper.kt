package com.master.validationhelper

import android.content.Context
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern


class ValidationHelper(
    private var anim: Animation? = null,
    val context: Context,
    val putStarInRequired: Boolean = false
) {

    init {
        if (anim == null) {
            anim = AnimationUtils.loadAnimation(context, R.anim.shake_anim)
        }
    }

    private val validator by lazy { Validator(anim) }
    private val validationList: ArrayList<ValidationModel> = ArrayList()

    /**
     * Simple field required Validation
     */
    fun addRequiredValidation(textInputLayout: TextInputLayout, blankMsg: String) {
        validator.errorRemoveOnChange(textInputLayout)
        validationList.add(
            ValidationModel(
                mTextInputLayout = textInputLayout,
                mErrMsg = blankMsg
            )
        )
        if (putStarInRequired && !textInputLayout.hint.toString().trim().endsWith("*"))
            textInputLayout.hint = textInputLayout.hint.toString() + "*"
    }

    fun addCustomLogicValidation(customLogicValidation: CustomLogicValidation) {
        validationList.add(
            ValidationModel(
                customLogicValidation = customLogicValidation
            )
        )
    }

    fun addRequiredValidation(editText: EditText, blankMsg: String) {
        validator.errorRemoveOnChange(editText)
        validationList.add(ValidationModel(mEditText = editText, mErrMsg = blankMsg))

        if (putStarInRequired && !editText.hint.toString().trim().endsWith("*"))
            editText.hint = editText.hint.toString() + "*"
    }


    /**
     * Email Address Validation
     */
    fun addEmailValidation(
        textInputLayout: TextInputLayout,
        blankEmailMsg: String,
        invalidEmailMsg: String,
        isRequired: Boolean = true
    ) {
        validator.errorRemoveOnChange(textInputLayout)
        if (isRequired) {
            validationList.add(
                ValidationModel(
                    mTextInputLayout = textInputLayout,
                    mErrMsg = blankEmailMsg
                )
            )
            if (putStarInRequired && !textInputLayout.hint.toString().trim().endsWith("*"))
                textInputLayout.hint = textInputLayout.hint.toString() + "*"

        }
        validationList.add(
            ValidationModel(
                mTextInputLayout = textInputLayout,
                mErrMsg = invalidEmailMsg,
                mPattern = EMAIL_ADDRESS,
                mIsRequired = isRequired
            )
        )
    }

    fun addEmailValidation(
        editText: EditText,
        blankEmailMsg: String,
        invalidEmailMsg: String,
        isRequired: Boolean = true
    ) {
        validator.errorRemoveOnChange(editText)
        if (isRequired) {
            validationList.add(ValidationModel(mEditText = editText, mErrMsg = blankEmailMsg))
            if (putStarInRequired && !editText.hint.toString().trim().endsWith("*"))
                editText.hint = editText.hint.toString() + "*"
        }
        validationList.add(
            ValidationModel(
                mEditText = editText,
                mErrMsg = invalidEmailMsg,
                mPattern = EMAIL_ADDRESS,
                mIsRequired = isRequired
            )
        )
    }


    /**
     * Mobile number Validation
     */
    fun addMobileValidation(
        textInputLayout: TextInputLayout,
        blankMobileNumberMsg: String,
        invalidMobileNumberMsg: String,
        isRequired: Boolean = true,
        mobileNumberPattern: Pattern = MOBILE
    ) {
        validator.errorRemoveOnChange(textInputLayout)
        if (isRequired) {
            validationList.add(
                ValidationModel(
                    mTextInputLayout = textInputLayout,
                    mErrMsg = blankMobileNumberMsg
                )
            )
            if (putStarInRequired && !textInputLayout.hint.toString().trim().endsWith("*"))
                textInputLayout.hint = textInputLayout.hint.toString() + "*"
        }
        validationList.add(
            ValidationModel(
                mTextInputLayout = textInputLayout,
                mErrMsg = invalidMobileNumberMsg,
                mPattern = mobileNumberPattern,
                mIsRequired = isRequired
            )
        )
    }

    fun addMobileValidation(
        editText: EditText,
        blankMobileNumberMsg: String,
        invalidMobileNumberMsg: String,
        isRequired: Boolean = true,
        mobileNumberPattern: Pattern = MOBILE
    ) {
        validator.errorRemoveOnChange(editText)
        if (isRequired) {
            validationList.add(
                ValidationModel(
                    mEditText = editText,
                    mErrMsg = blankMobileNumberMsg
                )
            )
            if (putStarInRequired && !editText.hint.toString().trim().endsWith("*"))
                editText.hint = editText.hint.toString() + "*"
        }
        validationList.add(
            ValidationModel(
                mEditText = editText,
                mErrMsg = invalidMobileNumberMsg,
                mPattern = mobileNumberPattern,
                mIsRequired = isRequired
            )
        )
    }

    /**
     * Strong and required password check
     */
    fun addPasswordValidation(
        textInputLayout: TextInputLayout, blankPasswordMsg: String, weakPasswordMSg: String = "",
        isRequired: Boolean = true, isStrong: Boolean = false,
        strongPattern: Pattern = STRONG_PASSWORD_CHECK
    ) {
        validator.errorRemoveOnChange(textInputLayout)
        if (isRequired) {
            validationList.add(
                ValidationModel(
                    mTextInputLayout = textInputLayout,
                    mErrMsg = blankPasswordMsg
                )
            )
            if (putStarInRequired && !textInputLayout.hint.toString().trim().endsWith("*"))
                textInputLayout.hint = textInputLayout.hint.toString() + "*"
        }
        if (isStrong)
            validationList.add(
                ValidationModel(
                    mTextInputLayout = textInputLayout,
                    mErrMsg = weakPasswordMSg,
                    mPattern = strongPattern,
                    mIsRequired = isRequired
                )
            )
    }

    fun addPasswordValidation(
        editText: EditText, blankPasswordMsg: String, weakPasswordMSg: String,
        isRequired: Boolean = true, isStrong: Boolean = false,
        strongPattern: Pattern = STRONG_PASSWORD_CHECK
    ) {
        validator.errorRemoveOnChange(editText)
        if (isRequired) {
            validationList.add(ValidationModel(mEditText = editText, mErrMsg = blankPasswordMsg))
            if (putStarInRequired && !editText.hint.toString().trim().endsWith("*"))
                editText.hint = editText.hint.toString() + "*"
        }
        if (isStrong)
            validationList.add(
                ValidationModel(
                    mEditText = editText,
                    mErrMsg = weakPasswordMSg,
                    mPattern = strongPattern,
                    mIsRequired = isRequired
                )
            )
    }

    /**
     * Confirm password Validation
     */
    fun addConfirmPasswordValidation(
        textInputLayout: TextInputLayout,
        confirmTextInputLayout: TextInputLayout,
        mismatchPasswordMsg: String = ""
    ) {
        validator.errorRemoveOnChange(textInputLayout)
        validator.errorRemoveOnChange(confirmTextInputLayout)
        validationList.add(
            ValidationModel(
                mTextInputLayout = textInputLayout,
                mConfirmationTextInputLayout = confirmTextInputLayout,
                mErrMsg = mismatchPasswordMsg
            )
        )
    }

    fun addConfirmPasswordValidation(
        editText: EditText,
        confirmEditText: EditText,
        mismatchPasswordMsg: String
    ) {
        validator.errorRemoveOnChange(editText)
        validator.errorRemoveOnChange(confirmEditText)
        validationList.add(
            ValidationModel(
                mEditText = editText,
                mConfirmationEditText = confirmEditText,
                mErrMsg = mismatchPasswordMsg
            )
        )
    }


    /**
     * Strong and required password check
     */
    fun addCustomePatternValidation(
        textInputLayout: TextInputLayout,
        patternMismatchMsg: String,
        pattern: Pattern
    ) {
        validator.errorRemoveOnChange(textInputLayout)
        validationList.add(
            ValidationModel(
                mTextInputLayout = textInputLayout,
                mErrMsg = patternMismatchMsg,
                mPattern = pattern
            )
        )
    }

    fun addCustomePatternValidation(
        editText: EditText,
        patternMismatchMsg: String,
        pattern: Pattern
    ) {
        validator.errorRemoveOnChange(editText)
        validationList.add(
            ValidationModel(
                mEditText = editText,
                mErrMsg = patternMismatchMsg,
                mPattern = pattern
            )
        )
    }


    fun clearAll() {
        validationList.clear()
        validationList.forEach {
            if (it.mTextInputLayout != null) {
                it.mTextInputLayout.hint = it.mTextInputLayout.hint.toString().replace("*", "")
            } else if (it.mEditText != null) {
                it.mEditText.hint = it.mEditText.hint.toString().replace("*", "")
            }
        }
    }

    fun validateAll(): Boolean {
        if (validationList.isNotEmpty()) {
            validationList.forEach {
                //Custom Regular express
                if (it.mTextInputLayout != null && it.mEditText == null) {
                    if (it.mPattern == null) {
                        if (it.mConfirmationTextInputLayout != null) {
                            if (!validator.validateTextInputConfirmPassword(it)) return false
                        } else {
                            if (!validator.validateTextInputRequired(it)) return false
                        }
                    } else {
                        if (!validator.validateTextInputPattern(it)) return false
                    }

                } else if (it.mEditText != null && it.mTextInputLayout == null) {
                    if (it.mPattern == null) {
                        if (it.mConfirmationTextInputLayout != null) {
                            if (!validator.validateEditTextConfirmPassword(it)) return false
                        } else {
                            if (!validator.validateEditTextRequired(it)) return false
                        }
                    } else {
                        if (!validator.validateEditTextPattern(it)) return false
                    }
                } else if (it.mEditText == null && it.mTextInputLayout == null) {
                    if (it.customLogicValidation?.isValid() == false) return false
                }
            }
        } else {
            Log.e("ValidationHelper",context.getString(R.string.msg_no_validation))
            return true
        }
        return true
    }


    companion object {
        val EMAIL_ADDRESS: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        val MOBILE: Pattern = Pattern.compile(
            "^\\d{7,12}$"
        )

        val STRONG_PASSWORD_CHECK: Pattern = Pattern.compile(
            "((?=.*\\d)(?=.*[A-Z])(?=.*[!@#\$&^%*]).{6,})"
        )
    }
}

