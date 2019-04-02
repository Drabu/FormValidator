package com.oneclickaway.opensource.formvalidationsexample

import android.databinding.DataBindingUtil
import android.os.Binder
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.oneclickaway.opensource.formvalidationsexample.databinding.ActivityMainBinding
import com.oneclickaway.opensource.validation.interfaces.OnResponseListener
import com.oneclickaway.opensource.validation.model.FormValidator
import javax.xml.validation.Validator
import kotlin.math.log

class MainActivity : AppCompatActivity(), OnResponseListener.OnFormValidationListener, OnResponseListener.OnFieldValidationListener,  View.OnClickListener{


    private lateinit var mainActivityMainBinding : ActivityMainBinding

    var myValidator = FormValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityMainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainActivityMainBinding.submitFormBTN.setOnClickListener(this)


        /*validate all of my fields in this views */
        myValidator.attachValidators(viewGroup = mainActivityMainBinding.mainLinearLayoutLL, onFieldValidationListener = this)


    }


    override fun onClick(view: View) {

        when(view.id){
            R.id.submitFormBTN -> {
                val optionalParams = intArrayOf(R.id.lastNameET)
                myValidator.isFormValidated( optionalParams = optionalParams,  onFormValidationListener = this, showErrors = true,  viewGroup = mainActivityMainBinding.mainLinearLayoutLL )
            }

        }

    }


    override fun onFormValidationListener(isFormFilled: Boolean) {

        if (isFormFilled){
            /*Form is filled*/
            Toast.makeText(this, "Form is filled", Toast.LENGTH_LONG).show()
        }else {
            /*form is not filled*/
            Toast.makeText(this, "Please fill the form!", Toast.LENGTH_LONG).show()

        }
    }


    override fun onFieldValidationListener(isFormValidated: Boolean) {

        Toast.makeText(this, "Validators attached" , Toast.LENGTH_LONG).show()

    }



}