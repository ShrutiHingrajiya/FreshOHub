package test.practical.com.androidprojectsstructuredemo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import test.practical.com.androidprojectsstructuredemo.Activity.Customer_Edit_Add_Address_Activity;
import test.practical.com.androidprojectsstructuredemo.R;
import test.practical.com.androidprojectsstructuredemo.RetroFit.ApiInterface;

public class Adapter_Saved_Address extends RecyclerView.Adapter<Adapter_Saved_Address.MyHolder> {
    Context c;
    List<String> arrayList;
    String hashToken, registerId;
    private ApiInterface apiService;

    boolean b;

    public Adapter_Saved_Address(Context c, List<String> arrayList, boolean b) {
        this.c = c;
        this.arrayList = arrayList;
        this.b = b;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_saved_address, parent, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

            holder.imgAddress.setBackgroundResource(R.drawable.lhome);
            holder.lnEditDelete.setVisibility(View.VISIBLE);

        holder.txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, Customer_Edit_Add_Address_Activity.class);
                i.putExtra("ADDRESS", "EDIT");
                i.putExtra("SELECTED_ADDRESS", holder.edtAddress.getText());
                i.putExtra("SELECTED_TYPE", holder.edtAddress.getText());
                i.putExtra("SELECTED_LAT", "");
                i.putExtra("SELECTED_LNG", "");
                i.putExtra("ADDRESS_ID", "");
                c.startActivity(i);
                ((Activity) c).finish();
            }
        });

        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtEdit, txtDelete;
        EditText edtAddress;
        ImageView imgAddress;
        View viewAddress;
        LinearLayout lnEditDelete;

        public MyHolder(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtEdit = (TextView) itemView.findViewById(R.id.txtEdit);
            txtDelete = (TextView) itemView.findViewById(R.id.txtDelete);
            edtAddress = (EditText) itemView.findViewById(R.id.edtAddress);
            imgAddress = (ImageView) itemView.findViewById(R.id.imgAddress);
           // viewAddress = (View) itemView.findViewById(R.id.viewAddress);
            lnEditDelete = (LinearLayout) itemView.findViewById(R.id.lnEditDelete);

        }
    }
}

