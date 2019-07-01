package vn.com.it.truongpham.demomvvm.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import vn.com.it.truongpham.demomvvm.R;
import vn.com.it.truongpham.demomvvm.data.model.User;

public class MainActivity extends AppCompatActivity {
    ViewModelMain viewModelMain;
    RecyclerView rvUser;
    AdapterUser adapterUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvUser=findViewById(R.id.rvUser);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvUser.setLayoutManager(linearLayoutManager);


        observableViewModel();


    }

    private void observableViewModel() {
        viewModelMain=ViewModelProviders.of(this).get(ViewModelMain.class);

        viewModelMain.getListUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                adapterUser=new AdapterUser(users);
                rvUser.setAdapter(adapterUser);
            }
        });

        viewModelMain.getError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean error) {

            }
        });

        viewModelMain.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean loading) {

            }
        });

    }
}
