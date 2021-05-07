package com.codepath.appointsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.codepath.appointsy.databinding.ActivityBusinessProfileBinding;
import com.codepath.appointsy.databinding.FragmentBusinessProfileBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

public class BusinessProfileActivity extends AppCompatActivity {

    private ActivityBusinessProfileBinding binding;
    private ImageView ivProfile;
    private TextInputLayout tvName;
    private EditText etName;
    private TextInputLayout tvUsername;
    private EditText etUsername;
    private TextInputLayout tvOwner;
    private EditText etOwner;
    private TextInputLayout tvEmail;
    private EditText etEmail;
    private TextInputLayout tvPhoneNumber;
    private EditText etPhoneNumber;
    private TextInputLayout tvPassword;
    private EditText etPassword;
    private TextInputLayout tvBio;
    private EditText etBio;
    private TextInputLayout tvHours;
    private EditText etHours;
    private TextInputLayout tvPrice;
    private EditText etPrice;
    private TextInputLayout tvLocation;
    private EditText etLocation;
    private TextInputLayout tvType;
    private EditText etType;
    private MaterialButton btUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_business_profile);

        Intent i = getIntent();
        ParseUser user = Parcels.unwrap((ParseUser) i.getParcelableExtra("user"));
        ivProfile = binding.ivProfile;
        tvName = binding.tvName;
        tvUsername = binding.tvUsername;
        tvOwner = binding.tvOwner;
        tvEmail = binding.tvEmail;
        tvPhoneNumber = binding.tvPhoneNumber;
        tvPassword = binding.tvPassword;
        tvBio = binding.tvBio;
        tvHours = binding.tvHours;
        tvPrice = binding.tvPrice;
        tvLocation = binding.tvLocation;
        tvType = binding.tvType;
        btUpdate = binding.btUpdate;
        etName = binding.etName;
        etUsername = binding.etUsername;
        etOwner = binding.etOwner;
        etEmail = binding.etEmail;
        etPhoneNumber = binding.etPhoneNumber;
        etPassword = binding.etPassword;
        etBio = binding.etBio;
        etHours = binding.etHours;
        etPrice = binding.etPrice;
        etLocation = binding.etLocation;
        etType = binding.etType;

        //Set text for each field
        etName.setText(user.getString("fullName"));
        etUsername.setText(user.getUsername());
        etEmail.setText(user.getEmail());
        etPassword.setText(user.getString("password"));
        etBio.setText(user.getString("userBio"));
        etPhoneNumber.setText(user.getString("phoneNumber"));

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereExists(user.getUsername());
        query.findInBackground((users, e) -> {
            if(users == null){
                ParseObject newBusiness = new ParseObject("BusinessProfile");
                newBusiness.saveInBackground();
                ParseUser newUser = new ParseUser();
                newUser.setUsername(binding.tvName.getEditText().getText().toString());
                newUser.setEmail(binding.tvEmail.getEditText().getText().toString());
                newUser.setPassword(binding.tvPassword.getEditText().getText().toString());
                newUser.put("fullName", binding.tvName.getEditText().getText().toString());
                newUser.put("userBio", binding.tvBio.getEditText().getText().toString());
                newUser.put("phoneNumber",binding.tvPhoneNumber.getEditText().getText().toString());
                newUser.put("businessProfileID", newBusiness.get("objectID").toString());

                newUser.signUpInBackground();

            }
            else{
                ParseQuery<ParseObject> bQuery = ParseQuery.getQuery("BusinessProfile");
                bQuery.whereEqualTo("objectID", user.get("businessProfileID").toString());
                bQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject business, ParseException e) {
                        etOwner.setText(business.getString("ownerName"));
                        //etHours.setText(business.getString("businessHours"));
                        etPrice.setText("$" + business.getString("servicePrice"));
                        etLocation.setText(business.getString("location"));
                        etType.setText(business.getString("businessType"));
                    }
                });
            }
        });

        btUpdate.setOnClickListener((e ->{
            // TODO check if inputs are not null

            //user.setUsername(binding.tvName.getEditText().getText().toString());
            //Currently not planning for email update, but left in for testing purposes.
            //user.setEmail(binding.tvEmail.getEditText().getText().toString());
            user.put("phoneNumber", binding.tvPhoneNumber.getEditText().getText().toString());
            user.setPassword(binding.tvPassword.getEditText().getText().toString());
            user.put("bio", binding.tvBio.getEditText().getText().toString());
            user.saveInBackground();

            ParseQuery<ParseObject> bQuery = ParseQuery.getQuery("BusinessProfile");
            bQuery.whereEqualTo("objectID", user.get("businessProfileID").toString());
            bQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject business, ParseException e) {
                    business.put("ownerName", binding.tvOwner.getEditText().getText().toString());
                    //business.put("businessHours", binding.tvHours.getEditText().getText().toString());
                    business.put("servicePrice", binding.tvPrice.getEditText().getText().toString());
                    business.put("location", binding.tvLocation.getEditText().getText().toString());
                    business.put("businessType", binding.tvType.getEditText().getText().toString());
                }
            });

            Toast.makeText(this, "Profile update successful!", Toast.LENGTH_SHORT).show();
        }));
    }
}

