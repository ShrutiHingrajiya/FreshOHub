package test.practical.com.androidprojectsstructuredemo.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.practical.com.androidprojectsstructuredemo.Adapter.CategoryDashboard_Adapter;
import test.practical.com.androidprojectsstructuredemo.Adapter.CategoryProduct_Adapter;
import test.practical.com.androidprojectsstructuredemo.Adapter.TopDealsDashboard_Adapter;
import test.practical.com.androidprojectsstructuredemo.R;

public class ProductWithCategoryActivity extends AppCompatActivity {

    @BindView(R.id.rcv_category)
    RecyclerView rcvCategory;
    @BindView(R.id.rcv_product_category)
    RecyclerView rcvProductCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_with_category);
        ButterKnife.bind(this);

        this.setMemoryAllocation();
    }

    private void setMemoryAllocation() {

        CategoryDashboard_Adapter adt = new CategoryDashboard_Adapter(ProductWithCategoryActivity.this,false);
        rcvCategory.setLayoutManager(new LinearLayoutManager(ProductWithCategoryActivity.this, RecyclerView.HORIZONTAL, false));
        rcvCategory.setAdapter(adt);

        CategoryProduct_Adapter adt1 = new CategoryProduct_Adapter(ProductWithCategoryActivity.this);
        rcvProductCategory.setLayoutManager(new GridLayoutManager(ProductWithCategoryActivity.this, 2));
        rcvProductCategory.setAdapter(adt1);

    }
}
