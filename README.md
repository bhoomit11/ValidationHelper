

![alt text](https://raw.githubusercontent.com/bhoomit11/ValidationHelper/master/logo/validation_helper_logo.png)


Library to define all validation condition at single place and handle all validation in a class in simple way
Follow below step to add this in to your project

Step 1. Add the dependency

   	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add it in your root build.gradle at the end of repositories:

    dependencies {
    	implementation 'com.github.bhoomit11:ValidationHelper:1.0.0'
    }
  
  
Create object of validation helper in class which you want to use validations

    private val validationHelper: ValidationHelper by lazy {
        ValidationHelper(
            context = this,
            anim = null, // Pass custom animation if you need, if not then pass null, shake anim is default
            putStarInRequired = true // Set this as true if * needed in required fields in you project
        )
    }
    
"anim" is animation object you want when validation error accurs, default is shake error animation is there in library
"putStarInRequired" set true if you want * in your required fields in your form

You can add different validation like this in validationHelper

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
	 3+
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
	
	/*
         * your own custom logic validation
         */
	 
	 validatorHelper.addCustomLogicValidation(object : CustomLogicValidation {
            override fun isValid(): Boolean {
                return if (checkbox.isChecked) {
                    true
                } else {
		    // Here you can show your error msg for custom logic
                    showSnackbar(getString(R.string.msg_enter_enter_time_for_x, itemNotFilled.getFullWeekName()))
                    false
                }
            }
	 })

to clear out all validation added in validationHelper class
    
    validationHelper.clearAll() // If you want to clear all previous validations added


to validate added validation 

    button.setOnClickListener {
            if (validationHelper.validateAll()) {
                // Put you logic here if all validations are passed
            }
        }
	
	
Checkout sample for more info.
