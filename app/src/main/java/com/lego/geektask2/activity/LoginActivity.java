package com.lego.geektask2.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.lego.geektask2.R;
import com.lego.geektask2.Utils.MyCallback;
import com.lego.geektask2.fragment.LoginFragment;
import com.lego.geektask2.fragment.RecoveryPasswordFragment;
import com.lego.geektask2.fragment.RegistrationFragment;

public class LoginActivity extends AppCompatActivity implements MyCallback{

    private FragmentManager manager;
    private static ProgressDialog pd;
    static Context context;
    private LoginFragment loginFragment;
    public static final String SOCIAL_NETWORK_TAG = "SocialIntegrationMain.SOCIAL_NETWORK_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        loginFragment =  LoginFragment.newInstance(this);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.FragmentConteiner, loginFragment).commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SOCIAL_NETWORK_TAG);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
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
}
