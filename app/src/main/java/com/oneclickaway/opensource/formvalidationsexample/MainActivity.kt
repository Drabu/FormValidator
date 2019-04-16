package com.oneclickaway.opensource.formvalidationsexample

import android.os.Binder
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.oneclickaway.opensource.formvalidationsexample.databinding.ActivityMainBinding
import com.oneclickaway.opensource.validation.interfaces.OnResponseListener
import com.oneclickaway.opensource.validation.model.FormValidator
import kotlinx.android.synthetic.main.activity_main.*
import javax.xml.validation.Validator
import kotlin.math.log

class MainActivity : AppCompatActivity(), OnResponseListener.OnFormValidationListener {


    val TAG = javaClass.simpleName

    lateinit var submitForm  : Button
    lateinit var mainLinearLayoutLL  : LinearLayout



    var myValidator = FormValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitForm = findViewById(R.id.submitFormBTN)
        mainLinearLayoutLL = findViewById(R.id.mainLinearLayoutLL)

        submitForm.setOnClickListener{
            myValidator.isFormValidated(mainLinearLayoutLL, this, true)
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



    override fun onDestroy() {
        super.onDestroy()

        FormValidator.unbind()

    }


}