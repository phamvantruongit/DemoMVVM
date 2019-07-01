package vn.com.it.truongpham.demomvvm.data.rest;

import java.util.Map;
import java.util.Observable;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import vn.com.it.truongpham.demomvvm.data.response.BaseResponse;
import vn.com.it.truongpham.demomvvm.data.response.UserListResponse;

public interface RestApi {

  @GET("users.php")
  io.reactivex.Observable<UserListResponse> getListUser();

  @POST("user.php")
  io.reactivex.Observable<BaseResponse> addUser(@FieldMap Map<String,String> map);


  @POST("update.php")
  io.reactivex.Observable<BaseResponse> updateUser(@FieldMap Map<String,String> map);

}
