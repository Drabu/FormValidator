package com.oneclickaway.opensource.validation.interfaces

import com.oneclickaway.opensource.validation.model.ValidationResult


object OnResponseListener{

    interface OnFormValidationListener {
        fun onFormValidationTaskSuccess (isFormFilled: Boolean)
        fun onFormValidationError(error : Throwable)
    }

    interface OnFieldValidationListener {
        fun onFieldValidationListener (isFormValidated: Boolean)
    }

}

