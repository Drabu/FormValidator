package com.oneclickaway.opensource.validation.model

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.oneclickaway.opensource.validation.interfaces.OnResponseListener
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

object FormValidator {

  val TAG = javaClass.simpleName
  var compositeDisposable  = CompositeDisposable()

  private fun checkIfFieldLeftBlank(v: ViewGroup, oe: ObservableEmitter<View>, optionalParams: IntArray) {
        for (i in 0 until v.childCount) {
            if (v.getChildAt(i) is EditText) {

                if ((v.getChildAt(i) as EditText).text.toString().isEmpty() && !(optionalParams.contains(v.getChildAt(i).id))) {
                    /*edit text is empty*/
                    oe.onNext(v.getChildAt(i))
                }
                Log.d(TAG , "Parent is Layout " + ((v.getChildAt(i) as EditText ).parent is TextInputLayout))
            } else if (v.getChildAt(i) is ViewGroup) {
                checkIfFieldLeftBlank(v.getChildAt(i) as ViewGroup, oe, optionalParams)

            }
        }
    }

  private fun eraseWhenStartedTyping(editText: EditText, message: String) {

            Log.i(TAG, "Text Watcher attached")

            editText.addTextChangedListener(object : TextWatcher{

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    if (editText.text.toString().isEmpty()) {
                        setErrorForTIL(true, editText,  message)
                        Log.i(TAG, "Field is empty")
                    }else {
                        setErrorForTIL(false, editText,  message)
                        Log.i(TAG, "Field has data")
                    }
                }
            })

        }

  private fun setErrorForTIL(enabled  : Boolean, editText : EditText, message: String){

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

  fun clearFormValidator() { compositeDisposable.clear() }

  fun isFormFilled(viewGroup: ViewGroup, onFormValidationListener: OnResponseListener.OnFormValidationListener, message : String = "Required", errorEnabled : Boolean = true, optionalParams:IntArray = intArrayOf()){

        /*here we assume the form is filled*/
        var isFormFilled = true

        compositeDisposable.add(

                Observable.create<View> {

                    checkIfFieldLeftBlank(viewGroup, it, optionalParams)

                    it.onComplete()

                }
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .distinctUntilChanged()
                        .subscribeWith(object : DisposableObserver<View>() {
                            override fun onComplete() {
                                onFormValidationListener.onFormValidationTaskSuccess(isFormFilled)
                            }

                            override fun onNext(view: View) {
                                setError(view)
                            }

                            private fun setError(view :View) {

                                /*since we have an empty filed so our assumption was wrong*/
                                isFormFilled  = false

                                if ( errorEnabled ) {

                                    if ( view.parent is ViewGroup ){
                                        if ( (view.parent as ViewGroup).parent is  TextInputLayout) {
                                            eraseWhenStartedTyping((view as EditText), message)
                                            setErrorForTIL(true, view , message)
                                        }else {
                                            (view as EditText).error = message
                                        }

                                        Log.d(TAG, " ${(view.parent as ViewGroup).parent.javaClass.simpleName} ")

                                    }else {
                                        (view as EditText).error = message
                                    }

                                }

                            }

                            override fun onError(e: Throwable) {
                                onFormValidationListener.onFormValidationError(e)
                            }

                        })


        )

    }


}



