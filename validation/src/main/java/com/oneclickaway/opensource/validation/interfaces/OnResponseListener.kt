package com.oneclickaway.opensource.validation.interfaces

/*** @author buren* */
object OnResponseListener {
    /** @author @buren ---> {methods to }*/
    interface OnFormValidationListener {
        /*** @author buren*
         * @param isFormFilled contains the result of the process. contains true if the form is filled and vice-versa
         * invoked when form validation process completes successfully
         * */
        fun onFormValidationTaskSuccess(isFormFilled: Boolean)

        /*** @author buren
         * @param error Specifies the error occurred
         *invoked if the form validation did not complete **/
        fun onFormValidationError(error: Throwable)

    }

}

