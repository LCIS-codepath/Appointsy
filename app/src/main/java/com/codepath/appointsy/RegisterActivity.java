package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codepath.appointsy.databinding.ActivityRegisterBinding;
import com.codepath.appointsy.fragments.ProfileFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;
import com.parse.ParseUser;

public class RegisterActivity extends AppCompatActivity {

    private final String TAG = "RegisterActivity";
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
            user.setUsername(binding.tiUsername.getEditText().getText().toString());
            user.setEmail(binding.tiEmail.getEditText().getText().toString());
            user.setPassword(binding.tiPassword.getEditText().getText().toString());
            user.put("fullName", binding.tiName.getEditText().getText().toString());
            user.put("phoneNumber", binding.tiPhoneNum.getEditText().getText().toString());
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

                Toast.makeText(this, "Register successful!", Toast.LENGTH_SHORT).show();
//                Intent intent;
//                if(!cbBusiness.isChecked()){
//                    // navigate to client profile
//                    // TODO navigate to respective setup profiles, pass the current Inputs through Intents
//                    intent = new Intent(this, ProfileFragment.class);
//                }
//                else {
//                    // cb is checked, navigate to business profile
//                    intent = new Intent(this, ProfileFragment.class);
//                    // pass data (check if we can store the object instead POJO
//                }
//                intent.putExtra("username", user.getUsername());
//                intent.putExtra("email", user.getEmail());
//                intent.putExtra("password", user.get("password").toString());
//                intent.putExtra("fullName", user.get("fullName").toString());
//                intent.putExtra("phoneNumber", user.get("phoneNumber").toString());
//                startActivity(intent);
            }
        });
    }
}