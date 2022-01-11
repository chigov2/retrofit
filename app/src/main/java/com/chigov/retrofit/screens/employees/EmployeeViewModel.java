package com.chigov.retrofit.screens.employees;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.chigov.retrofit.api.ApiFactory;
import com.chigov.retrofit.api.ApiService;
import com.chigov.retrofit.data.AppDatabase;
import com.chigov.retrofit.pojo.Employee;
import com.chigov.retrofit.pojo.EmployeeResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeViewModel extends AndroidViewModel {
    private static AppDatabase db;
    private LiveData<List<Employee>> employees;

    private MutableLiveData<Throwable> errors;

    private Disposable disposable;
    private CompositeDisposable compositeDisposable;
    public LiveData<Throwable> getErrors() {
        return errors;
    }

    public void clearErrors(){
        errors.setValue(null);
    }

    //constructor
    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application);
        employees = db.employeeDao().getEmployees();
        errors = new MutableLiveData<>();
    }
    //getter
    public LiveData<List<Employee>> getEmployees() {
        return employees;
    }

    @SuppressWarnings("unchecked")
    private void insertEmployees(List<Employee> employees){
        new InsertEmployeeTask().execute(employees);
    }

    private static class InsertEmployeeTask extends AsyncTask<List<Employee>,Void,Void>{
        @Override
        protected final Void doInBackground(List<Employee>... lists) {
            if (lists != null && lists.length > 0){
                db.employeeDao().insertEmployees(lists[0]);
            }
            return null;
        }
    }

    //удаление данных из базы
    private void deleteAllEmployees(){
        new DeleteAllEmployeesTask().execute();
    }

    private static class DeleteAllEmployeesTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            db.employeeDao().deleteAllEmployees();
            return null;
        }
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
                        deleteAllEmployees();
                        insertEmployees(employeeResponse.getEmployeesFromInternet());

                    }
                },new Consumer<Throwable>(){
                    @Override
                    public void accept(Throwable throwable) {
                        //errors
                        errors.setValue(throwable);//private MutableLiveData<Throwable> errors;
                    }
                });
        compositeDisposable.add(disposable);

    }

    @Override//метод вызывается при уничтожении viewModel
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}
