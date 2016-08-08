package com.lego.geektask2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.core.listener.OnLoginCompleteListener;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.github.gorbin.asne.twitter.TwitterSocialNetwork;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lego.geektask2.R;
import com.lego.geektask2.Utils.MyCallback;
import com.lego.geektask2.activity.LoginActivity;
import com.lego.geektask2.activity.MainActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoginFragment extends Fragment  implements SocialNetworkManager.OnInitializationCompleteListener,
        OnLoginCompleteListener,   GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private EditText inputName, inputPassword;
    private TextInputLayout inputLayoutName, inputLayoutPassword;
    private Button btnSignUp, btnRegistr, btnForgot;
    private ImageButton btnFacebook, btnTwitter, btnGoogle;
    private FragmentManager manager;
    private static MyCallback myCallback;
    public static SocialNetworkManager mSocialNetworkManager;
    private static final int RC_SIGN_IN = 9001;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(MyCallback Callback) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        initSocial();
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

        btnFacebook = (ImageButton) view.findViewById(R.id.buttonFacebook);
        btnFacebook.setOnClickListener(loginClick);
        btnTwitter = (ImageButton) view.findViewById(R.id.buttonTwitter);
        btnTwitter.setOnClickListener(loginClick);
        btnGoogle = (ImageButton) view.findViewById(R.id.buttonGoogle);
        btnGoogle.setOnClickListener(MyOnClickListener);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
    }

    private void initSocial(){
        mSocialNetworkManager = (SocialNetworkManager) getFragmentManager().findFragmentByTag(LoginActivity.SOCIAL_NETWORK_TAG);

        String TWITTER_CONSUMER_KEY = getActivity().getString(R.string.twitter_consumer_key);
        String TWITTER_CONSUMER_SECRET = getActivity().getString(R.string.twitter_consumer_secret);
        String TWITTER_CALLBACK_URL = "oauth://ASNE";

        ArrayList<String> fbScope = new ArrayList<String>();
        fbScope.addAll(Arrays.asList("public_profile, email, user_friends"));


        //Check if manager exist
        if (mSocialNetworkManager == null) {
            mSocialNetworkManager = new SocialNetworkManager();

            //Init and add to manager FacebookSocialNetwork
            FacebookSocialNetwork fbNetwork = new FacebookSocialNetwork(this, fbScope);
            mSocialNetworkManager.addSocialNetwork(fbNetwork);

            //Init and add to manager TwitterSocialNetwork
            TwitterSocialNetwork twNetwork = new TwitterSocialNetwork(this, TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET, TWITTER_CALLBACK_URL);
            mSocialNetworkManager.addSocialNetwork(twNetwork);

            //Initiate every network from mSocialNetworkManager
            getFragmentManager().beginTransaction().add(mSocialNetworkManager, LoginActivity.SOCIAL_NETWORK_TAG).commit();
            mSocialNetworkManager.setOnInitializationCompleteListener(this);
        } else {
            //if manager exist - get and setup login only for initialized SocialNetworks
            if(!mSocialNetworkManager.getInitializedSocialNetworks().isEmpty()) {
                List<SocialNetwork> socialNetworks = mSocialNetworkManager.getInitializedSocialNetworks();
                for (SocialNetwork socialNetwork : socialNetworks) {
                    socialNetwork.setOnLoginCompleteListener(this);
                }
            }
        }
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();

// Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
//        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
//                .enableAutoManage(getActivity(), this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
    }


    @Override
    public void onSocialNetworkManagerInitialized() {
        //when init SocialNetworks - get and setup login only for initialized SocialNetworks
        for (SocialNetwork socialNetwork : mSocialNetworkManager.getInitializedSocialNetworks()) {
            socialNetwork.setOnLoginCompleteListener(this);
        }
    }


    private View.OnClickListener loginClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int networkId = 0;
            switch (view.getId()){
                case R.id.buttonFacebook:
                    networkId = FacebookSocialNetwork.ID;
                    break;
                case R.id.buttonTwitter:
                    networkId = TwitterSocialNetwork.ID;
                    break;
            }
            SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(networkId);
            if(!socialNetwork.isConnected()) {
                if(networkId != 0) {
                    socialNetwork.requestLogin();
                    LoginActivity.showProgress("Loading");
                } else {
                    Toast.makeText(getActivity(), "Wrong networkId", Toast.LENGTH_LONG).show();
                }
            } else {
                submitSocial();
            }
        }
    };

    @Override
    public void onLoginSuccess(int socialNetworkID) {
        LoginActivity.hideProgress();
        submitSocial();
    }

    @Override
    public void onError(int socialNetworkID, String requestID, String errorMessage, Object data) {
        LoginActivity.hideProgress();
        Toast.makeText(getActivity(), "ERROR: " + errorMessage, Toast.LENGTH_LONG).show();
    }

    private View.OnClickListener MyOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_signup:
                    submitForm();
                    break;
                case R.id.buttonForgotPass:
                    myCallback.toRecover();
                    break;
                case R.id.buttonRegistration:
                    myCallback.toRegistration();
                    break;
                case R.id.buttonGoogle:
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                    break;

            }
        }
    };

    /**
     * Validating form
     */
    private void submitSocial() {
        Intent intent = new Intent(getContext(),MainActivity.class);
        startActivity(intent);
    }

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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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
