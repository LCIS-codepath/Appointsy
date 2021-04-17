package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.codepath.appointsy.databinding.ActivityRegisterBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    private MaterialCheckBox cbBusiness;
    private MaterialButton btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        cbBusiness = binding.cbBusiness;
        btnConfirm = binding.btnConfirm;

        btnConfirm.setOnClickListener((e) -> {

            /* can convert to ternary operator instead */

            // TODO navigate to respective setup profiles, pass the current Inputs
            // for auto fill

            // if cb is not selected
            if(!cbBusiness.isChecked()){
                // navigate to client profile
            }
            else {
                // cb is checked, navigate to business profile
            }
        });

    }
}