package com.lego.geektask2.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lego.geektask2.Logic.Task1.Palindrome;
import com.lego.geektask2.Logic.Task2.FactorialSum;
import com.lego.geektask2.Logic.Task3.Pairs;
import com.lego.geektask2.R;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    @Bind(R.id.input_layout_value)
    TextInputLayout mInputLayoutName;

    @Bind(R.id.textViewAnswer)
    TextView mAnswer;

    @Bind(R.id.input_value)
    EditText mInputValue;

    public PageFragment() {
        // Required empty public constructor
    }


    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        ButterKnife.bind(this, view);
        init(view);

        if (mPage == 3) {
            mInputLayoutName.setHint(getString(R.string.hint_pairs));
            mInputValue.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            mInputLayoutName.setHint(getString(R.string.hint_tabs));
            mInputValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        return view;
    }

    private void init(View view) {
        mInputLayoutName = ButterKnife.findById(view, R.id.input_layout_value);
        mInputValue = ButterKnife.findById(view, R.id.input_value);
        mAnswer = ButterKnife.findById(view, R.id.textViewAnswer);
        Button btCalculate = ButterKnife.findById(view, R.id.buttonCalculate);
        btCalculate.setOnClickListener(myListener);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mPage == 1) {
                Palindrome palindrome = Palindrome.getInstance();
                mAnswer.setText(palindrome.start());
            } else if (mPage == 2) {
                FactorialSum factorialSum = FactorialSum.getInstance();
                if (Integer.valueOf(mInputValue.getText().toString()) > 1000) {
                    mAnswer.setText(R.string.error_large_number);
                } else {
                    mAnswer.setText(factorialSum.start(Integer.valueOf(mInputValue.getText().toString())));
                }
            } else {
                Pairs pairs = Pairs.getInstance();
                if (checkWithRegExp(mInputValue.getText().toString())) {
                    String temp[] = mInputValue.getText().toString().split(" ");
                    int[][] pairsArray = new int[temp.length][2];

                    for (int i = 0; i < temp.length; i++) {
                        pairsArray[i][0] = Integer.valueOf(temp[i].substring(0, 1));
                        pairsArray[i][1] = Integer.valueOf(temp[i].substring(1, 2));
                    }
                    mAnswer.setText(pairs.start(pairsArray));
                } else {
                    mAnswer.setText(R.string.error_pairs_number);
                }

            }
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static boolean checkWithRegExp(String userNameString) {
        Pattern p = Pattern.compile("(\\d{2}+\\s*){2,}");
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }
}
