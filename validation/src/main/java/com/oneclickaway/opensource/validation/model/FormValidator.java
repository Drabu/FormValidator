package com.oneclickaway.opensource.validation.model;

import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.oneclickaway.opensource.validation.interfaces.OnResponseListener;

public class FormValidator {


    public void isFormValidated(OnResponseListener onResponseListener, ViewGroup viewGroup, Boolean showErrors){
         new ValidateForm(showErrors,  onResponseListener).execute(viewGroup);
    }


    private static class ValidateForm extends AsyncTask<ViewGroup, View, Void> {

        Boolean isFormFilled;
        Boolean showErrors;
        OnResponseListener onResponseListener;


        ValidateForm(Boolean showError, OnResponseListener onResponseListener) {
            this.onResponseListener = onResponseListener;
            this.showErrors = showError;
            isFormFilled = true;
        }

        @Override
        protected Void doInBackground(ViewGroup... viewGroups) {
            checkIfFieldLeftBlank( viewGroups[0]);
            return  null;
        }

        @Override
        protected void onProgressUpdate(View... values) {
            super.onProgressUpdate(values);
            if (showErrors) ((EditText) values[0]).setError("Required");
            isFormFilled = false;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            onResponseListener.onResponse(isFormFilled);
        }

        void checkIfFieldLeftBlank(final ViewGroup v){
            for (int i = 0; i < v.getChildCount(); i++){
                if ( v.getChildAt(i) instanceof EditText ){
                    if (((EditText) v.getChildAt(i)).getText().toString().length() == 0){
                        publishProgress(v.getChildAt(i));
                    }
                }else if ( v.getChildAt(i) instanceof ViewGroup){
                    checkIfFieldLeftBlank((ViewGroup) v.getChildAt(i));
                }
            }
        }
    }

}


