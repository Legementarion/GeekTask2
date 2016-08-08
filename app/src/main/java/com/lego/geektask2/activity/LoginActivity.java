package com.lego.geektask2.activity;


import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lego.geektask2.R;
import com.lego.geektask2.Utils.MyCallback;
import com.lego.geektask2.fragment.LoginFragment;
import com.lego.geektask2.fragment.RecoveryPasswordFragment;
import com.lego.geektask2.fragment.RegistrationFragment;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity implements MyCallback, GoogleApiClient.OnConnectionFailedListener {

    private FragmentManager manager;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private static ProgressDialog pd;
    static Context context;
    private LoginFragment loginFragment;
    public static final String SOCIAL_NETWORK_TAG = "SocialIntegrationMain.SOCIAL_NETWORK_TAG";
    private final static String USERINFO_SCOPE =
            "https://www.googleapis.com/auth/userinfo.profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        loginFragment = LoginFragment.newInstance(this, mGoogleApiClient);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.FragmentConteiner, loginFragment).commit();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 74537) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(SOCIAL_NETWORK_TAG);
            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }

    }

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("!!!!", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            // Signed out, show unauthenticated UI.

        }
    }

    @Override
    public void backToLogin() {
        manager.beginTransaction().replace(R.id.FragmentConteiner, loginFragment).commit();
    }

    @Override
    public void toRecover() {
        RecoveryPasswordFragment recoveryPasswordFragment = RecoveryPasswordFragment.newInstance(this);
        manager.beginTransaction().replace(R.id.FragmentConteiner, recoveryPasswordFragment).commit();
    }

    @Override
    public void toRegistration() {
        RegistrationFragment registrationFragment = RegistrationFragment.newInstance(this);
        manager.beginTransaction().replace(R.id.FragmentConteiner, registrationFragment).commit();
    }


    public static void showProgress(String message) {
        pd = new ProgressDialog(context);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage(message);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    public static void hideProgress() {
        pd.dismiss();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
