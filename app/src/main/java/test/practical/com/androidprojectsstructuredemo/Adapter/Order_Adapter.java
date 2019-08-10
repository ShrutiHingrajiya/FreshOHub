package test.practical.com.androidprojectsstructuredemo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.practical.com.androidprojectsstructuredemo.Activity.OrderActivity;
import test.practical.com.androidprojectsstructuredemo.R;

public class Order_Adapter extends RecyclerView.Adapter<Order_Adapter.Myholder> {

    Context c;
    int SelectedPosition = 100;
    int oldselected = 100;
    public Boolean Anim =false;

    public Order_Adapter(OrderActivity c) {
        this.c = c;

    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_order, parent, false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

//        SelectedPosition =position;
        holder.orderDetail.setVisibility(View.GONE);



        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedPosition = position;
                    notifyDataSetChanged();

            }
        });

        if (SelectedPosition == position){

            holder.orderDetail.setVisibility(View.VISIBLE);
            Animation slide_down = AnimationUtils.loadAnimation(c,
                    R.anim.slid_up_animation);

            Log.e("Selected",SelectedPosition +"-- Old --"+oldselected);

            if (oldselected != SelectedPosition)
            {
                holder.orderDetail.startAnimation(slide_down);
                slide_down.setFillBefore(true);
                oldselected =SelectedPosition;
            }
            else {
                holder.orderDetail.setVisibility(View.GONE);
                oldselected =100;
            }


        }
        else {
            holder.orderDetail.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class Myholder extends RecyclerView.ViewHolder {

        @BindView(R.id.order_detail)
        LinearLayout orderDetail;
        @BindView(R.id.cv_main)
        CardView cvMain;

        public Myholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
