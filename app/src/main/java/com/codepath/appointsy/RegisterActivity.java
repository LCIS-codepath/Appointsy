package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codepath.appointsy.databinding.ActivityRegisterBinding;
import com.codepath.appointsy.fragments.BusinessProfileFragment;
import com.codepath.appointsy.fragments.ProfileFragment;
import com.codepath.appointsy.objects.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.Objects;

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
            User user = new User();
            // TODO check if inputs are not null
            user.setUsername(Objects.requireNonNull(binding.tiUsername.getEditText()).getText().toString());
            user.setEmail(Objects.requireNonNull(binding.tiEmail.getEditText()).getText().toString());
            user.setPassword(Objects.requireNonNull(binding.tiPassword.getEditText()).getText().toString());
            user.setFullName(Objects.requireNonNull(binding.tiName.getEditText()).getText().toString());
            user.setPhoneNumber(Objects.requireNonNull(binding.tiPhoneNum.getEditText()).getText().toString());
            routeToFragment(user);
        });

    }

    private void routeToFragment(User user) {
        // check User data is taken
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereExists(user.getUsername());
        query.whereExists(user.getPhoneNumber());
        query.whereExists(user.getEmail());
        query.findInBackground((users, e) -> {
            if(users != null){
                Snackbar.make(llRegister, "Register failed. Username/Number/Email taken.", Snackbar.LENGTH_SHORT).show();
            } else {
                // No users have the combination
                Toast.makeText(this, "Register successful!", Toast.LENGTH_SHORT).show();
                Intent intent;
                if(!cbBusiness.isChecked()){
                    // navigate to client profile
                    intent = new Intent(this, ProfileActivity.class);
                }
                else {
                    // cb is checked, navigate to business profile
                    // TODO BusinessProfileActivity needed
                    intent = new Intent(this, BusinessProfileActivity.class);
                }
                // Add user object and pass to respective setup
                intent.putExtra("user", Parcels.wrap(user));
                startActivity(intent);
            }
        });
    }

}