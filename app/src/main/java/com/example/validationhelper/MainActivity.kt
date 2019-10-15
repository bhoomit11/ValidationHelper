package com.example.validationhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.master.validationhelper.ValidationHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    companion object {
        val ACCOUNT_NUMBER_PATTERN: Pattern = Pattern.compile(
            "^\\d{10}\$"
        )

        val MOBILE_PATTERN: Pattern = Pattern.compile(
            "^\\d{7,12}$"
        )

        val STRONG_PASSWORD_PATTERN: Pattern = Pattern.compile(
            "((?=.*\\d)(?=.*[A-Z])(?=.*[!@#\$&^%*]).{6,})"
        )
    }

    private val validationHelper: ValidationHelper by lazy {
        ValidationHelper(
            anim = null, //Pass custom animation if you need, if not pass null, shake anim is default
            context = this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set this as true if * needed in required fields in you project
        validationHelper.putStarInRequired = true

        // call add validation after init UI
        addValidation()

        button.setOnClickListener {
            if (validationHelper.validateAll()) {
                // Put you logic here if all validations are passed
            }
        }
    }

    /**
     * Adding validations in sequence with TextInputLayout or EditText field
     */
    private fun addValidation() {

        validationHelper.clearAll() // If you want to clear all previous validations added

        /*
         * Simple required field validation
         */
        validationHelper.addRequiredValidation(
            textInputLayout = tilName,
            blankMsg = "Enter full name" // Error message if blank
        )

        /*
         * Email field validation
         */
        validationHelper.addEmailValidation(
            textInputLayout = tilEmail,
            blankEmailMsg = "Enter email address", // Error message if blank
            invalidEmailMsg = "Enter valid email", // Error message if invalid
            isRequired = true
        )

        /*
         * Mobile field validation
         */
        validationHelper.addMobileValidation(
            textInputLayout = tilMobile,
            blankMobileNumberMsg = "Enter mobile number", // Error message if blank
            invalidMobileNumberMsg = "Enter valid mobile number", // Error message if invalid
            isRequired = true,
            mobileNumberPattern = MOBILE_PATTERN // add your pattern if you need, but default is available
        )


        /*
         * Simple required field validation
         */
        validationHelper.addRequiredValidation(
            textInputLayout = tilBankAccountNumber,
            blankMsg = "Enter bank account number" // Error message if blank
        )

        /*
         * Custom field validation (Add you own pattern validation)
         */
        validationHelper.addCustomePatternValidation(
            textInputLayout = tilBankAccountNumber,
            patternMismatchMsg = "Invalid bank account number", // Error message if pattern not match
            pattern = ACCOUNT_NUMBER_PATTERN
        )

        /*
         * Password field validation, with strong password enable option
         */
        validationHelper.addPasswordValidation(
            textInputLayout = tilPassword,
            blankPasswordMsg = "Enter password", // Error message if blank
            weakPasswordMSg = "Enter strong password", // Error message if not strong password
            isRequired = true,
            isStrong = true,
            strongPattern = STRONG_PASSWORD_PATTERN // add your pattern if you need, but default is available
        )

        /*
         * confirm password field validation
         */
        validationHelper.addConfirmPasswordValidation(
            textInputLayout = tilPassword, // Add this param as your password field
            confirmTextInputLayout = tilConfirmPassword, // Add this param as your confirm password field
            mismatchPasswordMsg = "Password and confirm password must be same" // Error message if not matched
        )


    }
}
