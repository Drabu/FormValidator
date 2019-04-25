package com.oneclickaway.opensource.validation.interfaces

/**
 * @author buren
 * @since 07.07.17
 */
object OnResponseListener {

    interface OnFormValidationListener {
        fun onFormValidationTaskSuccess(isFormFilled: Boolean)
        fun onFormValidationError(error: Throwable)
    }

}

