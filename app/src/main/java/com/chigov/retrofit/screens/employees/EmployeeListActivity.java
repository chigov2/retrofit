package com.chigov.retrofit.screens.employees;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chigov.retrofit.R;
import com.chigov.retrofit.adapters.EmployeeAdapter;
import com.chigov.retrofit.pojo.Employee;

import java.util.ArrayList;
import java.util.List;


public class EmployeeListActivity extends AppCompatActivity implements EmployeeListView{
    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;
    private EmployeeListPresenter presenter;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new EmployeeListPresenter(this);
        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployees);
        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<>());
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);
        presenter.loadData();
    }

    @Override
    protected void onDestroy() {
        presenter.disposeDisposable();
        super.onDestroy();
    }

    @Override
    public void showData(List<Employee> employees) {
        adapter.setEmployees(employees);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
    }

}

//    List<Employee> employees = new ArrayList<>();
//    Employee employee1 = new Employee();
//    Employee employee2 = new Employee();
//        employee1.setfName("Max");
//                employee2.setfName("Ivan");
//                employee1.setlName("Ivanov");
//                employee2.setlName("Petrov");
//                employees.add(employee1);
//                employees.add(employee2);
//                adapter.setEmployees(employees);