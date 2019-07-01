package vn.com.it.truongpham.demomvvm.ui.postuser;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import vn.com.it.truongpham.demomvvm.base.MyApplication;
import vn.com.it.truongpham.demomvvm.data.model.User;

public class ViewModelUser extends ViewModel {

    private MutableLiveData<Integer> code = new MutableLiveData<>();
    private MutableLiveData<String> message = new MutableLiveData<>();
    private MutableLiveData<User> userLive = new MutableLiveData<>();
    private MutableLiveData<Boolean> checkData=new MutableLiveData<>();
    private CompositeDisposable disposable;

    public ViewModelUser() {
        disposable = new CompositeDisposable();
    }

    public void setUser(User user) {
        userLive.setValue(user);
        pushUser();
    }

    public void updateUser(User user){
        userLive.setValue(user);
        updateUser();
    }

    public LiveData<Integer> getCode() {
        return code;
    }

    public LiveData<String> getMessage(){
        return message;
    }


    private void pushUser() {
        userLive.observeForever(user -> {
            Map<String, String> param = new HashMap<>();
            param.put("name", user.name);
            param.put("address", user.address);
            param.put("age", String.valueOf(user.age));
            disposable.add(MyApplication.restApi.addUser(param).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {

                        code.setValue(data.code);
                        message.setValue(data.message);


                    }, throwable -> {
                        message.setValue("Error");

                    })
            );
        });
    }

    private void updateUser() {
        userLive.observeForever(user -> {
            Map<String, String> param = new HashMap<>();
            param.put("id", String.valueOf(user.id));
            param.put("name", user.name);
            param.put("address", user.address);
            param.put("age", String.valueOf(user.age));
            disposable.add(MyApplication.restApi.updateUser(param).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {

                        code.setValue(data.code);
                        message.setValue(data.message);

                    }, throwable -> {
                        message.setValue("Error");

                    })
            );
        });
    }



    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
