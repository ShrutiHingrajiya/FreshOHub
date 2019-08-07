package test.practical.com.androidprojectsstructuredemo.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.practical.com.androidprojectsstructuredemo.Adapter.CategoryDashboard_Adapter;
import test.practical.com.androidprojectsstructuredemo.Adapter.TopDealsDashboard_Adapter;
import test.practical.com.androidprojectsstructuredemo.R;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.rcv_top_deals_dashboard)
    RecyclerView rcvTopDealsDashboard;
    @BindView(R.id.rcv_category_dashboard)
    RecyclerView rcvCategoryDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        this.setMemoryAllocation();
    }

    private void setMemoryAllocation() {

        TopDealsDashboard_Adapter adt = new TopDealsDashboard_Adapter(DashboardActivity.this);
        rcvTopDealsDashboard.setLayoutManager(new LinearLayoutManager(DashboardActivity.this, RecyclerView.HORIZONTAL, false));
        rcvTopDealsDashboard.setAdapter(adt);

        CategoryDashboard_Adapter adt1 = new CategoryDashboard_Adapter(DashboardActivity.this, true);
        rcvCategoryDashboard.setLayoutManager(new LinearLayoutManager(DashboardActivity.this, RecyclerView.HORIZONTAL, false));
        rcvCategoryDashboard.setAdapter(adt1);
    }
}
