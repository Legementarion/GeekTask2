package com.lego.geektask2.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lego.geektask2.Logic.Task2.FactorialSum;
import com.lego.geektask2.R;


public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private TextInputLayout mInputLayoutName;
    private EditText mInputValue;
    private TextView mAnswer;

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
        Button btCalculate = (Button) view.findViewById(R.id.buttonCalculate);
        btCalculate.setOnClickListener(myListener);
        mInputLayoutName = (TextInputLayout) view.findViewById(R.id.input_layout_value);
        mInputValue = (EditText) mInputLayoutName.findViewById(R.id.input_name);
        if (mPage == 3) {
            mInputLayoutName.setHint("Enter The pairs (Example 13 14 34 56)");
//            mInputValue.setRawInputType(InputType.TYPE_CLASS_TEXT); error null pointer
        } else {
            mInputLayoutName.setHint("Enter the number");
        }
        return view;
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mPage == 1) {

            } else if (mPage == 2) {
                FactorialSum factorialSum = new FactorialSum();
                mAnswer.setText(factorialSum.start(Integer.valueOf(mInputValue.getText().toString())));
            } else {

            }
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
