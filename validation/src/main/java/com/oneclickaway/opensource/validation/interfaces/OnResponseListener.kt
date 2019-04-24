package com.oneclickaway.opensource.validation.interfaces


object OnResponseListener {

    interface OnFormValidationListener {
        fun onFormValidationTaskSuccess(isFormFilled: Boolean)
        fun onFormValidationError(error: Throwable)
    }

}

