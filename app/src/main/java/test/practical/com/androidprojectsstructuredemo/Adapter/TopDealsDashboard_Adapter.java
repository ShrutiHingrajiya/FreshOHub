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

public class TopDealsDashboard_Adapter extends RecyclerView.Adapter<TopDealsDashboard_Adapter.Myholder> {

    Context c;

    public TopDealsDashboard_Adapter(Activity c) {
        this.c = c;

    }

    @NonNull
    @Override
    public TopDealsDashboard_Adapter.Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_top_deals_dashboard, parent, false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopDealsDashboard_Adapter.Myholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class Myholder extends RecyclerView.ViewHolder{

        public Myholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
