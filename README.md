FormValidator
========

This library  allows you to validate forms, just pass the root layout and you will get to know whether the edit text fields are filled in the view are not without referencing them one by one.

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

    import com.oneclickaway.opensource.formvalidationsexample.databinding.ActivityMainBinding;
    import com.oneclickaway.opensource.validation.interfaces.OnResponseListener;
    import com.oneclickaway.opensource.validation.model.FormValidator;
    
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
   



In case you have optional parameters : 
        
 	val optionalInput = intArrayOf(R.id.optionalFirstET, R.id.optionalSecondET)
	FormValidator().isFormValidated( onResponseListener = this,  viewGroup = mainActivityMainBinding.mainLinearLayoutLL,
	optionalParams = optionalInput)
	

If you want to show errors: 
        
	FormValidator().isFormValidated( onResponseListener = this,  viewGroup = mainActivityMainBinding.mainLinearLayoutLL, 
	showErrors = true)
	


You can show your custom error message: 
        
	FormValidator().isFormValidated( onResponseListener = this,  viewGroup = mainActivityMainBinding.mainLinearLayoutLL, 
	showErrors = true, message="Field can't be left blank.")


	
	


<p align="center">
    <img src="demo.gif" alt="Demonstartion image."/>
</p>


Usage
-----
-Validates form without the hasle of refering to each edit text and checking each input box.
-Can set errors if enabled
-Can skip optional values if you have any.. 

