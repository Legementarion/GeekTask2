package com.lego.geektask2.activity;


import android.os.Handler;
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
    private boolean doubleBackToExitPressedOnce;
    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginFragment =  LoginFragment.newInstance(this);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.FragmentConteiner, loginFragment).commit();
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
}
