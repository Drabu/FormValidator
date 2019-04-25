FormValidator
========
Easily Valdiate huge forms

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3fcd52642ad44de7b6addb0b6ea30419)](https://app.codacy.com/app/Drabu/FormValidator?utm_source=github.com&utm_medium=referral&utm_content=Drabu/FormValidator&utm_campaign=Badge_Grade_Dashboard)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![](https://jitpack.io/v/Drabu/FormValidator.svg)](https://jitpack.io/#Drabu/FormValidator)

This library allows you to validate Huge Forms containing Fields in android just by a single line, It saves you from the hassle of checking individual edit text boxes one by one then setting the error.
This library supports edit texts view and no custom view is requred. it also supports error validation for TextInputLayouts. 

The library is written in kotlin and is build on top of RX Java and it provides methods for error handling as well. 

Example is mentioned in the project:

### How it works

<p align="center">
    <img src="demo.gif" alt="Demonstartion image."/>
</p>

Configuration
-------------

Add it in your root build.gradle at the end of repositories:

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Add the dependency: 

    dependencies {
	         implementation 'com.github.Drabu:FormValidator:1.1.3'
	 }

In case you have optional parameters : 
        
 	val optionalInput = intArrayOf(R.id.optionalFirstET, R.id.optionalSecondET)
	FormValidator.isFormFilled( optionalParams = optionalInput, onResponseListener = this,
	viewGroup = mainLinearLayoutLL)
	
If you want to show errors: 
        
	FormValidator.isFormFilled( onResponseListener = this,  viewGroup = mainLinearLayoutLL, 
	errorEnabled = true)
	

You can show your custom error message: 
        
	FormValidator.isFormFilled( onResponseListener = this,  viewGroup = mainLinearLayoutLL, 
	errorEnabled = true, message="Field can't be left blank.")

#Example Kotlin Class: 

    import com.oneclickaway.opensource.validation.interfaces.OnResponseListener
    import com.oneclickaway.opensource.validation.model.FormValidator
    
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
   
Usage
-----
-Minimum sdk 15
-Validates form without the hasle of refering to each edit text and checking each input box.
-Can set errors if enabled
-Can skip optional values if you have any.. 
