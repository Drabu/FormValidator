package com.oneclickaway.opensource.formvalidationsexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.oneclickaway.opensource.validation.interfaces.OnResponseListener
import com.oneclickaway.opensource.validation.model.FormValidator

/**
 * @author buren
 * @since 07.07.17
 */
class MainActivity : AppCompatActivity(), OnResponseListener.OnFormValidationListener {

    val TAG = javaClass.simpleName

    lateinit var submitForm  : Button
    lateinit var mainLinearLayoutLL  : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitForm = findViewById(R.id.submitFormBTN)
        mainLinearLayoutLL = findViewById(R.id.mainLinearLayoutLL)

        submitForm.setOnClickListener{
            FormValidator.isFormFilled(mainLinearLayoutLL, this, optionalParams = intArrayOf(R.id.lastNameET))
        }

    }

    override fun onFormValidationTaskSuccess(isFormFilled: Boolean) {
        /*Here isFormFilled represents that weather the form is filled or not*/

        if (isFormFilled){
            Toast.makeText(this, "Form filled", Toast.LENGTH_LONG).show()
        }else {
            Toast.makeText(this, "Form is not yet filled, Please fill the form.", Toast.LENGTH_LONG).show()
        }

    }

    override fun onFormValidationError(error: Throwable) {
        /*this method gives you a way of handling error if there is any*/
    }


    override fun onDestroy() {
        super.onDestroy()

        /*Be sure to call this in your onDestroy method to unBind the validator*/
        FormValidator.clearFormValidator()

    }



}