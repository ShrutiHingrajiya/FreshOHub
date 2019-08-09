package test.practical.com.androidprojectsstructuredemo.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.practical.com.androidprojectsstructuredemo.Adapter.Cart_Adapter;
import test.practical.com.androidprojectsstructuredemo.R;

public class FreshOHub_CartActivity extends AppCompatActivity {

    Context ctx;
    Cart_Adapter adpter;

    @BindView(R.id.mycart_list)
    RecyclerView mycartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_ohub__cart);
        ButterKnife.bind(this);

        CallMyRecycellist();
    }

    private void CallMyRecycellist() {
        adpter = new Cart_Adapter(ctx);
        @SuppressLint("WrongConstant") LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), OrientationHelper.VERTICAL, false);
        mycartList.setLayoutManager(layoutManager);
        mycartList.setAdapter(adpter);
    }
}
