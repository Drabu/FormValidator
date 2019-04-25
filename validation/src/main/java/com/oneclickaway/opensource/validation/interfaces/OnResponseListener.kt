package com.oneclickaway.opensource.validation.interfaces

/**
 * @author buren
 * @since 07.07.17
 */
object OnResponseListener {

    interface OnFormValidationListener {
        /*** @author buren* @since 07.07.17*/
        fun onFormValidationTaskSuccess(isFormFilled: Boolean)

        /*** @author buren* @since 07.07.17*/
        fun onFormValidationError(error: Throwable)
    }

}

