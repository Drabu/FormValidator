package com.oneclickaway.opensource.validation.model

import android.os.AsyncTask
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.oneclickaway.opensource.validation.interfaces.OnResponseListener

class FormValidator {

    /*optional  arrays*/
    fun isFormValidated(showErrors: Boolean, onResponseListener: OnResponseListener, viewGroup: ViewGroup, optionalParams: Array<Int>?){
        ValidateForm(optionalParams =optionalParams,  showErrors =  showErrors, onResponseListener =  onResponseListener).execute(viewGroup)
    }
}

private class ValidateForm (var optionalParams:Array<Int>? ,  var showErrors: Boolean, var onResponseListener: OnResponseListener) : AsyncTask<ViewGroup, View, Void>() {

    var isFormFilled: Boolean = true

    override fun doInBackground(vararg viewGroups: ViewGroup): Void? {
        checkIfFieldLeftBlank(viewGroups[0])
        return null
    }

    override fun onProgressUpdate(vararg values: View) {
        super.onProgressUpdate(*values)
        if (showErrors) (values[0] as EditText).error = "Required"
        isFormFilled = false
    }


    override fun onPostExecute(aVoid: Void?) {
        super.onPostExecute(aVoid)
        onResponseListener.onResponse(isFormFilled)
    }

    fun checkIfFieldLeftBlank(v: ViewGroup) {
        for (i in 0 until v.childCount) {
            if (v.getChildAt(i) is EditText) {
//                v.getChildAt(i).id != optionalParams.
                if ((v.getChildAt(i) as EditText).text.toString().isEmpty() ) {
                    publishProgress(v.getChildAt(i))
                }
            } else if (v.getChildAt(i) is ViewGroup) {
                checkIfFieldLeftBlank(v.getChildAt(i) as ViewGroup)
            }
        }
    }
}