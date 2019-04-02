package com.oneclickaway.opensource.validation.interfaces


object OnResponseListener{

    interface OnFormValidationListener {
        fun onFormValidationListener (isFormFilled: Boolean)
    }

    interface OnFieldValidationListener {
        fun onFieldValidationListener (isFormValidated: Boolean)
    }
}

