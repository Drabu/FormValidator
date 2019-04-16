package com.oneclickaway.opensource.validation.model

import android.os.AsyncTask
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.oneclickaway.opensource.validation.interfaces.OnResponseListener

class FormValidator {



    fun isFormValidated(viewGroup: ViewGroup, onFormValidationListener: OnResponseListener.OnFormValidationListener, showErrors: Boolean = false, optionalParams: IntArray = intArrayOf(), message: String = "Required"){
        ValidateForm(message =  message,  optionalParams =optionalParams,  showErrors =  showErrors, onFormValidationListener =  onFormValidationListener).execute(viewGroup)
    }



    fun attachValidators(viewGroup: ViewGroup, onFieldValidationListener: OnResponseListener.OnFieldValidationListener, optionalParams: IntArray = intArrayOf(), message: String = "Required"){
        AttachValidators( onFieldValidationListener = onFieldValidationListener,  optionalParams = optionalParams , message= message).execute(viewGroup)
    }

    companion object {

        var isBound = true


        private fun eraseWhenStartedTyping(editText: EditText, message: String) {

            Log.i("FormValidator", "Text Watcher attached")

            editText.addTextChangedListener(object : TextWatcher{

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    if (editText.text.toString().isEmpty()) {
                        setErrorForTIL(true, editText,  message)
                        Log.i("FormValidator", "Field is empty")
                    }else {
                        setErrorForTIL(false, editText,  message)
                        Log.i("FormValidator", "Field has data")
                    }
                }
            })

        }

        fun setErrorForTIL(enabled  : Boolean, editText : EditText, message: String){

            if (!isBound){ return }

            if (editText.parent is ViewGroup && (editText.parent as ViewGroup).parent is TextInputLayout){
                if (enabled){
                    ( (editText.parent as ViewGroup).parent as  TextInputLayout ).error = message
                }else {
                    ( (editText.parent as ViewGroup).parent as  TextInputLayout ).isErrorEnabled = false
                }

            }else {

                if (enabled){
                    editText.error = message
                }
            }

        }

        fun unbind() {
            isBound = false
        }

    }


    private class AttachValidators (  var message : String , var onFieldValidationListener: OnResponseListener.OnFieldValidationListener, var optionalParams:IntArray  ): AsyncTask<ViewGroup, View, Void> (){

        override fun doInBackground(vararg p0: ViewGroup): Void? {
            checkIfFieldLeftBlank(p0[0])
            return null
        }


        override fun onProgressUpdate(vararg values: View) {
            super.onProgressUpdate(*values)

            eraseWhenStartedTyping((values[0] as EditText),  message)

        }


        fun checkIfFieldLeftBlank(v: ViewGroup) {
            for (i in 0 until v.childCount) {
                if (v.getChildAt(i) is EditText) {
                    if (!(optionalParams.contains(v.getChildAt(i).id))) {
                        /*edit text is empty*/
                        publishProgress(v.getChildAt(i))
                    }
                } else if (v.getChildAt(i) is ViewGroup) {
                    checkIfFieldLeftBlank(v.getChildAt(i) as ViewGroup)

                }
            }
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            onFieldValidationListener.onFieldValidationListener(true)
        }

    }


    private class ValidateForm (var message : String , var showErrors: Boolean, var onFormValidationListener: OnResponseListener.OnFormValidationListener, var optionalParams:IntArray ) : AsyncTask<ViewGroup, View, Void>() {

        var isFormFilled: Boolean = true

        override fun doInBackground(vararg viewGroups: ViewGroup): Void? {
            checkIfFieldLeftBlank(viewGroups[0])
            return null
        }

        override fun onProgressUpdate(vararg values: View) {
            super.onProgressUpdate(*values)

            if (showErrors) {
                if ( values[0].parent is ViewGroup ){
                    if ( (values[0].parent as ViewGroup).parent is  TextInputLayout) {
                        eraseWhenStartedTyping((values[0] as EditText), message)
                        setErrorForTIL(true, values[0] as EditText , message)
                    }else {
                        (values[0] as EditText).error = message
                    }
                    Log.d("FormValidator", " ${(values[0].parent as ViewGroup).parent.javaClass.simpleName} ")
                }else {
                    (values[0] as EditText).error = message
                }
            }

            isFormFilled = false
        }


        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            onFormValidationListener.onFormValidationListener(isFormFilled)
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



}



