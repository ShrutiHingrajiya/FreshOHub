package test.practical.com.androidprojectsstructuredemo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.practical.com.androidprojectsstructuredemo.Activity.ProductWithCategoryActivity;
import test.practical.com.androidprojectsstructuredemo.R;

public class CategoryDashboard_Adapter extends RecyclerView.Adapter<CategoryDashboard_Adapter.Myholder> {

    Context c;
    boolean b;


    public CategoryDashboard_Adapter(Activity c, boolean b) {
        this.c = c;
        this.b = b;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_category_dashboard, parent, false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        holder.cardCategoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b) {
                    Intent i = new Intent(c, ProductWithCategoryActivity.class);
                    c.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class Myholder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_category_item)
        CardView cardCategoryItem;

        public Myholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
