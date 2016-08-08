package com.lego.geektask2.fragment;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lego.geektask2.R;
import com.lego.geektask2.Utils.MyCallback;
import com.lego.geektask2.Utils.Person;
import com.lego.geektask2.adapter.RvAdapter;

import java.util.ArrayList;
import java.util.List;

public class RegistrationFragment extends Fragment {


    private static MyCallback myCallback;
    private RecyclerView mRecyclerView;
    private List<Person> persons;


    private TextInputLayout[] InputTextReg;
    private EditText[] edtReg;
    private Button buttonRegistration;
    private FloatingActionButton floatingActionButtonAdd;
    private LinearLayout mChildrenLayout;
    private int[] mTemplate = {R.string.registration_children,
            R.string.registration_login,
            R.string.registration_date,
            R.string.registration_lastname,
            R.string.registration_password};

    private int mGlobal = 0;


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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(llm);
        persons = new ArrayList<>();
        persons.add(new Person("",getString(R.string.registration_firstname),getString(R.string.registration_lastname),
                getString(R.string.registration_email),getString(R.string.registration_phone)));
        persons.add(new Person(getString(R.string.registration_children),getString(R.string.registration_lastname),
                getString(R.string.registration_date),
                getString(R.string.registration_login),getString(R.string.registration_password)));
        RvAdapter adapter = new RvAdapter(persons);
        mRecyclerView.setAdapter(adapter);
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
                    pattern();
                    break;
            }
        }
    };

    private void pattern() {
        persons.add(new Person(getString(R.string.registration_children),getString(R.string.registration_lastname),
                getString(R.string.registration_date),
                getString(R.string.registration_login),getString(R.string.registration_password)));
        RvAdapter adapter = new RvAdapter(persons);
        mRecyclerView.setAdapter(adapter);
    }


    private void submitForm() {
        Toast.makeText(getContext(), R.string.registration_complete, Toast.LENGTH_SHORT).show();
        myCallback.backToLogin();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
