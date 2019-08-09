package test.practical.com.androidprojectsstructuredemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.practical.com.androidprojectsstructuredemo.Adapter.Adapter_Saved_Address;
import test.practical.com.androidprojectsstructuredemo.R;
import test.practical.com.androidprojectsstructuredemo.RetroFit.ApiInterface;


public class Customer_Saved_Address_Activity extends AppCompatActivity {

    @BindView(R.id.img_drawer_icon)
    ImageView imgDrawerIcon;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.ll_drawer_icon)
    LinearLayout llDrawerIcon;
    @BindView(R.id.txt_action_title)
    TextView txtActionTitle;
    @BindView(R.id.recyclerSavedAddress)
    RecyclerView recyclerSavedAddress;
    @BindView(R.id.lnAddNewAddress)
    LinearLayout lnAddNewAddress;
    List<String> listAddress = new ArrayList<String>();

    String hashToken, registerId;
    @BindView(R.id.savedAddress)
    TextView savedAddress;
    @BindView(R.id.imgAddAddress)
    ImageView imgAddAddress;
    @BindView(R.id.scroll)
    ScrollView scroll;
    @BindView(R.id.llnodata)
    LinearLayout llnodata;
    private ApiInterface apiService;
    boolean flagHome = false;
    boolean flagWork = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__saved__address_);
        ButterKnife.bind(this);



        this.setMemoryAllocation();

        this.setListeners();

        this.getSavedAddress();

    }

    private void getSavedAddress() {
        listAddress.add("a");
        listAddress.add("b");
        listAddress.add("c");

       recyclerSavedAddress.setLayoutManager(new LinearLayoutManager(Customer_Saved_Address_Activity.this));
       Adapter_Saved_Address adapter = new Adapter_Saved_Address(Customer_Saved_Address_Activity.this, listAddress, false);
       recyclerSavedAddress.setAdapter(adapter);

    }

    private void setMemoryAllocation() {


    }

    private void setListeners() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imgAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("flagHome____", flagHome + "____");
                Log.e("flagWork____", flagWork + "____");

                Intent i = new Intent(Customer_Saved_Address_Activity.this, Customer_Edit_Add_Address_Activity.class);
                i.putExtra("ADDRESS", "ADD");
                i.putExtra("FLAG_HOME", flagHome);
                i.putExtra("FLAG_WORK", flagWork);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
