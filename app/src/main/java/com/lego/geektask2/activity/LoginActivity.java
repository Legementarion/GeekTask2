package com.lego.geektask2.activity;


import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.lego.geektask2.R;
import com.lego.geektask2.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    private FragmentManager manager;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment =  LoginFragment.newInstance();
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.FragmentConteiner, loginFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(getApplicationContext(),R.string.doubleClick_backBtn,Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}
