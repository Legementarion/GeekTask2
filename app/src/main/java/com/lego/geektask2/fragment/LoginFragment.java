package com.lego.geektask2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.lego.geektask2.activity.MainActivity;


public class LoginFragment extends Fragment {


    private EditText inputName, inputPassword;
    private TextInputLayout inputLayoutName, inputLayoutPassword;
    private Button btnSignUp, btnRegistr, btnForgot;
    private FragmentManager manager;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        manager = getChildFragmentManager();
        inputLayoutName = (TextInputLayout) view.findViewById(R.id.input_layout_name);
        inputLayoutPassword = (TextInputLayout) view.findViewById(R.id.input_layout_password);
        inputName = (EditText) view.findViewById(R.id.input_name);
        inputName.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.profile, 0, 0, 0);
        inputPassword = (EditText) view.findViewById(R.id.input_password);
        inputPassword.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.password, 0, 0, 0);
        btnSignUp = (Button) view.findViewById(R.id.btn_signup);
        btnForgot = (Button) view.findViewById(R.id.buttonForgotPass);
        btnRegistr = (Button) view.findViewById(R.id.buttonRegistration);
        btnSignUp.setOnClickListener(MyOnClickListener);
        btnRegistr.setOnClickListener(MyOnClickListener);
        btnForgot.setOnClickListener(MyOnClickListener);


        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
    }


    private View.OnClickListener MyOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_signup:
                    submitForm();
                    break;
                case R.id.buttonForgotPass:
                    RecoveryPasswordFragment recoveryPasswordFragment = RecoveryPasswordFragment.newInstance();
                    Toast.makeText(getContext(), "buttonForgotPass", Toast.LENGTH_SHORT).show();
                    manager.beginTransaction().replace(R.id.FragmentConteiner, recoveryPasswordFragment).commit();
                    break;
                case R.id.buttonRegistration:
                    RegistrationFragment registrationFragment = RegistrationFragment.newInstance();
                    Toast.makeText(getContext(),"buttonRegistration", Toast.LENGTH_SHORT).show();
                    manager.beginTransaction().replace(R.id.FragmentConteiner, registrationFragment).commit();
                    break;
                case R.id.buttonFacebook:
                    break;
                case R.id.buttonTwiter:
                    break;
                case R.id.buttonGoogle:
                    break;
            }
        }
    };

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateName() && !validatePassword()) {
            return;
        }
        Intent intent = new Intent(getContext(),MainActivity.class);
        startActivity(intent);
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }


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
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
