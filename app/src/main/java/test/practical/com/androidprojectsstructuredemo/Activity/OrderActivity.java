package test.practical.com.androidprojectsstructuredemo.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.practical.com.androidprojectsstructuredemo.Adapter.Order_Adapter;
import test.practical.com.androidprojectsstructuredemo.R;

public class OrderActivity extends AppCompatActivity {


    @BindView(R.id.rcv_order)
    RecyclerView rcvOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        this.setMemoryAllocation();

    }

    private void setMemoryAllocation() {

        Order_Adapter adt = new Order_Adapter(OrderActivity.this);
        rcvOrder.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
        rcvOrder.setAdapter(adt);

    }
}
