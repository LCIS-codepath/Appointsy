package com.codepath.appointsy.sideBarFragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.appointsy.R;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessTypeModuleOverlay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessTypeModuleOverlay extends DialogFragment {

    private EditText etExample;
    private Button btnDone;

    ArrayList<String> arrayList_Search;
    ArrayAdapter<String> arrayAdapter_UserInput;


    public BusinessTypeModuleOverlay() {
        // Required empty public constructor
    }

    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }

    // 3. This method is invoked in the activity when the listener is triggered
    // Access the data result passed to the activity here

    public static BusinessTypeModuleOverlay newInstance(String title) {
        BusinessTypeModuleOverlay fragment = new BusinessTypeModuleOverlay();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_type_module_overlay, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputLayout generic_meu = view.findViewById(R.id.generic_meu);
        AutoCompleteTextView search_info = view.findViewById(R.id.search_info);
        btnDone = view.findViewById(R.id.btnDone);

        arrayList_Search = new ArrayList<>();
        arrayList_Search.add("barber shop");
        arrayList_Search.add("salon");
        arrayList_Search.add("food");
        arrayList_Search.add("taco");
        arrayList_Search.add("vegan");

        arrayAdapter_UserInput = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, arrayList_Search);
        search_info.setAdapter(arrayAdapter_UserInput);

        search_info.setThreshold(1);

        //pass a variable to intialize the array



        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        generic_meu.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        btnDone.setOnClickListener(v -> {
            // Return input text back to activity through the implemented listener
            EditNameDialogListener listener = (EditNameDialogListener) getActivity();
            listener.onFinishEditDialog(search_info.getText().toString());
            // Close the dialog and return back to the parent activity
            dismiss();
        });




    }

}