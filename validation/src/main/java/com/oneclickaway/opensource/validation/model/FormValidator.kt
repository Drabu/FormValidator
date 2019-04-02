package com.oneclickaway.opensource.validation.model

import android.os.AsyncTask
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.design.widget.TextInputLayout
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.oneclickaway.opensource.validation.interfaces.OnResponseListener
import java.util.stream.IntStream

class FormValidator {

    /*optional  arrays*/
    fun isFormValidated( viewGroup: ViewGroup, onResponseListener: OnResponseListener, showErrors: Boolean = false, optionalParams: IntArray = intArrayOf(), message: String = "Required"){
        ValidateForm(message =  message,  optionalParams =optionalParams,  showErrors =  showErrors, onResponseListener =  onResponseListener).execute(viewGroup)
    }
}

private class ValidateForm (var message : String , var showErrors: Boolean, var onResponseListener: OnResponseListener, var optionalParams:IntArray ) : AsyncTask<ViewGroup, View, Void>() {

    var isFormFilled: Boolean = true

    override fun doInBackground(vararg viewGroups: ViewGroup): Void? {
        checkIfFieldLeftBlank(viewGroups[0])
        return null
    }

    override fun onProgressUpdate(vararg values: View) {
        super.onProgressUpdate(*values)

        if (showErrors) (values[0] as EditText).error = message
        isFormFilled = false
    }

    override fun onPostExecute(aVoid: Void?) {
        super.onPostExecute(aVoid)
        onResponseListener.onResponse(isFormFilled)
    }

    fun checkIfFieldLeftBlank(v: ViewGroup) {
        for (i in 0 until v.childCount) {
            if (v.getChildAt(i) is EditText) {

                if ((v.getChildAt(i) as EditText).text.toString().isEmpty() && !(optionalParams.contains(v.getChildAt(i).id))) {
                    /*edit text is empty*/
                    publishProgress(v.getChildAt(i))
                }
                Log.d("FormValidator ", "Parent is Layout " + ((v.getChildAt(i) as EditText ).parent is TextInputLayout))
            } else if (v.getChildAt(i) is ViewGroup) {
                    checkIfFieldLeftBlank(v.getChildAt(i) as ViewGroup)

            }
        }
    }
}