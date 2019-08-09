package test.practical.com.androidprojectsstructuredemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.practical.com.androidprojectsstructuredemo.R;

public class FreshOHub_LoginActivity extends AppCompatActivity {

    @BindView(R.id.tv_Signup)
    TextView tvSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_ohub__login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_Signup)
    public void onViewClicked() {

        Intent i = new Intent(FreshOHub_LoginActivity.this, FreshOHub_SignupActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
