FormValidator
========

This library allows you to validate enormous Edit Text Fields in android just by a single line, It saves you from the hassle of checking individual edit text boxes one by one then setting the error.
The library also supports text input layouts for setting errors. The library uses RX Java 2 and provides methods for error handling as well. 

Example is mentioned in the project:

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
	         implementation 'com.github.Drabu:FormValidator:1.1.2'
	 }
   


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
   



In case you have optional parameters : 
        
 	val optionalInput = intArrayOf(R.id.optionalFirstET, R.id.optionalSecondET)
	FormValidator.isFormFilled( optionalParams = optionalInput, onResponseListener = this,  viewGroup = mainLinearLayoutLL)
	

If you want to show errors: 
        
	FormValidator.isFormFilled( onResponseListener = this,  viewGroup = mainLinearLayoutLL, errorEnabled = true)
	


You can show your custom error message: 
        
	FormValidator.isFormFilled( onResponseListener = this,  viewGroup = mainLinearLayoutLL, 
	errorEnabled = true, message="Field can't be left blank.")


	
	

### How it works

<p align="center">
    <img src="demo.gif" alt="Demonstartion image."/>
</p>


Usage
-----
-Minimum sdk 15
-Validates form without the hasle of refering to each edit text and checking each input box.
-Can set errors if enabled
-Can skip optional values if you have any.. 

