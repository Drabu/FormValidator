package com.oneclickaway.opensource.formvalidationsexample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
