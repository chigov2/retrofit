package com.chigov.retrofit.screens.employees;

import com.chigov.retrofit.api.ApiFactory;
import com.chigov.retrofit.api.ApiService;
import com.chigov.retrofit.pojo.EmployeeResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListPresenter {
    private Disposable disposable;
    private CompositeDisposable compositeDisposable;

    private EmployeeListView view;
    public EmployeeListPresenter(EmployeeListView view) {
        this.view = view;
    }

    //constructor
    //public EmployeeListPresenter(EmployeeListActivity activity) {
     //   this.activity = activity;
    //}

    public EmployeeListPresenter() {

    }

    public void loadData(){

        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        //получаем данные
        compositeDisposable = new CompositeDisposable();
        disposable = apiService.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>(){
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                        view.showData(employeeResponse.getResponse());
                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) {
                        view.showError();
                        //Log.i("test",throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }
    public void disposeDisposable(){
        if(compositeDisposable !=null){
        compositeDisposable.dispose();
        }
    }

}
