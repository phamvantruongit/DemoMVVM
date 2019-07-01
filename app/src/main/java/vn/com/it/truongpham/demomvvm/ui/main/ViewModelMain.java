package vn.com.it.truongpham.demomvvm.ui.main;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import vn.com.it.truongpham.demomvvm.base.MyApplication;
import vn.com.it.truongpham.demomvvm.data.model.User;

public class ViewModelMain extends ViewModel {

    private CompositeDisposable disposable;
    private final MutableLiveData<List<User>> listUser=new MutableLiveData<>();
    private final MutableLiveData<Boolean> userLoadError=new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading=new MutableLiveData<>();

    public ViewModelMain() {
        disposable=new CompositeDisposable();
        fetchUser();
    }

    LiveData<List<User>> getListUser(){
        return listUser;
    }

    LiveData<Boolean> getError() {
        return userLoadError;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

     public void fetchUser() {
        loading.setValue(true);
        disposable.add(MyApplication.restApi.getListUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data ->{
                   userLoadError.setValue(false);
                   listUser.setValue((List<User>) data);
                   loading.setValue(false);
                }, throwable ->{
                    userLoadError.setValue(true);
                    loading.setValue(false);
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(disposable!=null){
            disposable.clear();
            disposable=null;
        }
    }
}
