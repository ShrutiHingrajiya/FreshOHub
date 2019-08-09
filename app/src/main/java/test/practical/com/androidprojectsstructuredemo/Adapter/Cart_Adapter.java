package test.practical.com.androidprojectsstructuredemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import butterknife.ButterKnife;
import test.practical.com.androidprojectsstructuredemo.R;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.UserViewHolder> {


    private List<String> MyCartList;
    private Context context;

    public static class UserViewHolder extends RecyclerView.ViewHolder {


        public UserViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public Cart_Adapter(List<String> MyCartList, Context context) {
        this.MyCartList = MyCartList;
        this.context = context;

    }

    public Cart_Adapter(Context context) {
        this.MyCartList = MyCartList;
        this.context = context;

    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_cart, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    //here error will come in get so just clear it and then write getLeave_List_Month.this will directly give the suggestion
    public void onBindViewHolder(final UserViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {
        return 4;

    }
}
