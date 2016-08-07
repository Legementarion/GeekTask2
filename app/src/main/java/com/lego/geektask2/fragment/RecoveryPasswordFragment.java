package com.lego.geektask2.fragment;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lego.geektask2.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RecoveryPasswordFragment extends Fragment {

    @Bind(R.id.buttonRecover) Button btnRecover;
    @Bind(R.id.editTextRecover) EditText edtRecover;
    @Bind(R.id.input_layout_recover_password) TextInputLayout inputLayoutRecover;
    private FragmentManager manager;
    public RecoveryPasswordFragment() {
        // Required empty public constructor
    }


    public static RecoveryPasswordFragment newInstance() {
        RecoveryPasswordFragment fragment = new RecoveryPasswordFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recovery_password, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        btnRecover = ButterKnife.findById(view, R.id.buttonRecover);
        edtRecover = ButterKnife.findById(view, R.id.editTextRecover);
        inputLayoutRecover = ButterKnife.findById(view, R.id.input_layout_recover_password);
        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkWithRegExp(edtRecover.getText().toString())){
                    Toast.makeText(getContext(), R.string.new_password, Toast.LENGTH_SHORT).show();
                    LoginFragment loginFragment = LoginFragment.newInstance();
                    manager.beginTransaction().replace(R.id.FragmentConteiner, loginFragment).commit();
                }else {
                    inputLayoutRecover.setError(getString(R.string.Incorrect_email));
                }
            }
        });
    }

    private boolean checkWithRegExp(String userString) {
        Pattern p = Pattern.compile("(\\w+@[a-z,A-Z_]+?\\.[a-z,A-Z_]{2,6})");
        Matcher m = p.matcher(userString);
        return m.matches();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
