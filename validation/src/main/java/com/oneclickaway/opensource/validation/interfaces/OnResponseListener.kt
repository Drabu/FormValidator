package com.oneclickaway.opensource.validation.interfaces

/*** @author buren* */
object OnResponseListener {

    interface OnFormValidationListener {
        /*** @author buren* */
        fun onFormValidationTaskSuccess(isFormFilled: Boolean)

        /*** @author buren **/
        fun onFormValidationError(error: Throwable)

    }

}

