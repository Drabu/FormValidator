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
	        implementation 'com.github.Drabu:FormValidator:Tag'
	 }
   


#Example Java: 

    import com.oneclickaway.opensource.formvalidationsexample.databinding.ActivityMainBinding;
    import com.oneclickaway.opensource.validation.interfaces.OnResponseListener;
    import com.oneclickaway.opensource.validation.model.FormValidator;

    public class MainActivity extends AppCompatActivity implements OnResponseListener {
  
    ActivityMainBinding activityMainBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        /*make an instance for */
        FormValidator formValidator = new FormValidator();

        /*You need to pass the root layout*/ 
        /*create an OnResponseListener and attach to the method*/
        formValidator.isFormValidated(this, activityMainBinding.mainLinearLayoutLL, false );
        }

    @Override
    public void onResponse(boolean isFormFilled) {
    
        if (!isFormFilled){
            Toast.makeText(this, "Form is not filled", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Form is filled", Toast.LENGTH_SHORT).show();
        }
    }
    
    }


Usage
-----
-Validates form without the hasle of refering to each edit text and checking each input box.
-Can set errors if enabled


Thanks
------
