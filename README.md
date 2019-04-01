FormValidator
========

This library  allows you to validate forms, just pass the root layout and it will tell you whether the edit text fields are filled in the view are not.

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
    
    class MainActivity : AppCompatActivity(), OnResponseListener , View.OnClickListener{

    private lateinit var mainActivityMainBinding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityMainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainActivityMainBinding.submitFormBTN.setOnClickListener(this)


    }

    override fun onClick(view: View) {

        when(view.id){
            R.id.submitFormBTN -> {
	    	
                val optionalInput = intArrayOf(R.id.optionalFirstET, R.id.optionalSecondET)

                FormValidator().isFormValidated( onResponseListener = this,  viewGroup = mainActivityMainBinding.mainLinearLayoutLL,
	showErrors = true, optionalParams = optionalInput)
            }

        }

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
    
    }
   




Usage
-----
-Validates form without the hasle of refering to each edit text and checking each input box.
-Can set errors if enabled
-Can skip optional values if you have any.. 

