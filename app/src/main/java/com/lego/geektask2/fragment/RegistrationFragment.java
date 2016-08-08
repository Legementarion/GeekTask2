package com.lego.geektask2.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lego.geektask2.R;
import com.lego.geektask2.Utils.MyCallback;
import com.lego.geektask2.activity.MainActivity;

import butterknife.Bind;

public class RegistrationFragment extends Fragment {


    private static MyCallback myCallback;
    private TextInputLayout InputTextRegFirstname;
    private EditText edtRegFirstname;
    private Button buttonRegistration;
    private FloatingActionButton floatingActionButtonAdd;


    public RegistrationFragment() {
        // Required empty public constructor
    }


    public static RegistrationFragment newInstance(MyCallback Callback) {
        RegistrationFragment fragment = new RegistrationFragment();
        Bundle args = new Bundle();
        myCallback = Callback;
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
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        buttonRegistration = (Button) view.findViewById(R.id.buttonRegistrationSend);
        floatingActionButtonAdd = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonAdd);
        buttonRegistration.setOnClickListener(myOnclickListener);
        floatingActionButtonAdd.setOnClickListener(myOnclickListener);
    }

    private View.OnClickListener myOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.buttonRegistrationSend:
                    submitForm();
                    break;
                case R.id.floatingActionButtonAdd:
                    break;
            }
        }
    };

    private void submitForm() {
//        if (!validateName() && !validatePassword()) {
//            return;
//        }
        Toast.makeText(getContext(), R.string.registration_complete, Toast.LENGTH_SHORT).show();
        myCallback.backToLogin();
    }

//    private boolean validateName() {
//        if (inputName.getText().toString().trim().isEmpty()) {
//            inputLayoutName.setError(getString(R.string.err_msg_name));
//            requestFocus(inputName);
//            return false;
//        } else {
//            inputLayoutName.setErrorEnabled(false);
//        }
//
//        return true;
//    }
//
//    private boolean validatePassword() {
//        if (inputPassword.getText().toString().trim().isEmpty()) {
//            inputLayoutPassword.setError(getString(R.string.err_msg_password));
//            requestFocus(inputPassword);
//            return false;
//        } else {
//            inputLayoutPassword.setErrorEnabled(false);
//        }
//
//        return true;
//    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
//                    validateName();
                    break;
                case R.id.input_password:
//                    validatePassword();
                    break;
            }
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
