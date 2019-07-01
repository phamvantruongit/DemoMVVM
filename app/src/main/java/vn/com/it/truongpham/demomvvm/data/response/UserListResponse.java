package vn.com.it.truongpham.demomvvm.data.response;

import android.arch.lifecycle.LiveData;

import com.google.gson.annotations.SerializedName;

import vn.com.it.truongpham.demomvvm.data.model.User;

public class UserListResponse {
    @SerializedName("users")
    public LiveData<User> listUser;
}
