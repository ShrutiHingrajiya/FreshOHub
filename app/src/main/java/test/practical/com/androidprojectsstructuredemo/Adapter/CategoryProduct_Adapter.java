package test.practical.com.androidprojectsstructuredemo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;
import test.practical.com.androidprojectsstructuredemo.R;

public class CategoryProduct_Adapter extends RecyclerView.Adapter<CategoryProduct_Adapter.Myholder> {

    Context c;

    public CategoryProduct_Adapter(Activity c) {
        this.c = c;

    }

    @NonNull
    @Override
    public CategoryProduct_Adapter.Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_category_product_dashboard, parent, false);
        return new CategoryProduct_Adapter.Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryProduct_Adapter.Myholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class Myholder extends RecyclerView.ViewHolder {

        public Myholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}