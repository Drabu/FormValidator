package com.oneclickaway.opensource.validation.model

class Enhancements {

    /*  private class AttachValidators (  var message : String , var onFieldValidationListener: OnResponseListener.OnFieldValidationListener, var optionalParams:IntArray  ): AsyncTask<ViewGroup, View, Void> (){

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
                          *//*edit text is empty*//*
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
*/

    /*  private class ValidateForm (var message : String , var showErrors: Boolean, var onFormValidationListener: OnResponseListener.OnFormValidationListener, var optionalParams:IntArray ) : AsyncTask<ViewGroup, View, Void>() {

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


      }
  */


}