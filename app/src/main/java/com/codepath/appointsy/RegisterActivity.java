package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.codepath.appointsy.databinding.ActivityRegisterBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;
import com.parse.ParseUser;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    private MaterialCheckBox cbBusiness;
    private MaterialButton btnConfirm;
    private LinearLayout llRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        cbBusiness = binding.cbBusiness;
        btnConfirm = binding.btnConfirm;
        llRegister = binding.llRegister;

        btnConfirm.setOnClickListener((e) -> {
            ParseUser user = new ParseUser();
            // TODO check if inputs are not null
            user.setUsername(binding.tiUsername.getEditText().toString());
            user.setEmail(binding.tiEmail.getEditText().toString());
            user.setPassword(binding.tiPassword.getEditText().toString());
            user.put("fullName", binding.tiName.getEditText().toString());
            user.put("phoneNumber", binding.tiPassword.getEditText().getText());
            signUpFunc(user);
        });

    }

    private void signUpFunc(ParseUser user) {
        user.signUpInBackground((e) -> {
            // check if sign up has errors
            if(e != null){
                Snackbar.make(llRegister, "Register failed.", Snackbar.LENGTH_SHORT).show();
            } else {
                // register successful
                // if cb is not selected
                if(!cbBusiness.isChecked()){
                    // navigate to client profile
                    // TODO navigate to respective setup profiles, pass the current Inputs through Intents
                }
                else {
                    // cb is checked, navigate to business profile
                }
            }
        });
    }
}