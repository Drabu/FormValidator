package com.oneclickaway.opensource.formvalidationsexample

import android.databinding.DataBindingUtil
import android.os.Binder
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.oneclickaway.opensource.formvalidationsexample.databinding.ActivityMainBinding
import com.oneclickaway.opensource.validation.interfaces.OnResponseListener
import com.oneclickaway.opensource.validation.model.FormValidator
import kotlin.math.log

class MainActivity : AppCompatActivity(), OnResponseListener , View.OnClickListener{

    private lateinit var mainActivityMainBinding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityMainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainActivityMainBinding.submitFormBTN.setOnClickListener(this)


    }

    override fun onResponse(isFormFilled: Boolean) {

        if (isFormFilled){
            /*Form is filled*/
            Toast.makeText(this, "Form is filled", Toast.LENGTH_LONG).show()
        }else {
            /*form is not filled*/
            Toast.makeText(this, "Please fill the form!", Toast.LENGTH_LONG).show()

        }
    }

    override fun onClick(view: View) {

        when(view.id){
            R.id.submitFormBTN -> {
                val optionalInput = intArrayOf(R.id.optionalFirstET, R.id.optionalSecondET)

                FormValidator().isFormValidated( onResponseListener = this,  viewGroup = mainActivityMainBinding.mainLinearLayoutLL)
            }

        }

    }



}