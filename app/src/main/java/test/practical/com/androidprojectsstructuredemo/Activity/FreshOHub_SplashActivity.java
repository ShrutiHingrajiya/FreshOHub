package test.practical.com.androidprojectsstructuredemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.practical.com.androidprojectsstructuredemo.R;

public class FreshOHub_SplashActivity extends AppCompatActivity {

    Animation animFade;

    private static int SPLASH_TIME_OUT = 4000;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_ohub__splash);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(FreshOHub_SplashActivity.this,
                        Customer_Saved_Address_Activity.class);
                startActivity(i);
                finish();

            }
        }, SPLASH_TIME_OUT);

    }

}
