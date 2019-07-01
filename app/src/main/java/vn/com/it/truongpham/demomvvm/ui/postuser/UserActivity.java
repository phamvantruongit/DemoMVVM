package vn.com.it.truongpham.demomvvm.ui.postuser;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.com.it.truongpham.demomvvm.R;
import vn.com.it.truongpham.demomvvm.data.model.User;

public class UserActivity extends AppCompatActivity {
    EditText edName,edAddress,edAge;
    Button btnAddUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        edName=findViewById(R.id.edName);
        edAddress=findViewById(R.id.edAddress);
        edAge=findViewById(R.id.edAge);
        btnAddUser=findViewById(R.id.btnAddUser);
        ViewModelUser viewModelUser=ViewModelProviders.of(this).get(ViewModelUser.class);

        User data_user=getIntent().getParcelableExtra("user");
        if(data_user!=null){
            btnAddUser.setText("Edit user");
            edName.setText(data_user.name);
            edAddress.setText(data_user.address);
            edAge.setText(data_user.age);
        }



        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                user.name=edName.getText().toString();
                user.address=edAddress.getText().toString();
                user.age=edAge.getText().toString();
                if(data_user!=null){
                    viewModelUser.updateUser(user);
                }else {
                    viewModelUser.setUser(user);
                }

            }
        });


        viewModelUser.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                Toast.makeText(UserActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
