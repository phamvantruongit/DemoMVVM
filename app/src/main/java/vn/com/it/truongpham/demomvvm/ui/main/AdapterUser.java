package vn.com.it.truongpham.demomvvm.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.com.it.truongpham.demomvvm.R;
import vn.com.it.truongpham.demomvvm.data.model.User;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder> {
    List<User> userList;

    public AdapterUser(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
       viewHolder.tvName.setText(userList.get(i).name);
       viewHolder.tvAge.setText(userList.get(i).age);
    }



    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvAge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvAge =itemView.findViewById(R.id.tvAge);
        }
    }
}
