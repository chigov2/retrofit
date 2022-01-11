package com.chigov.retrofit.screens.employees;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chigov.retrofit.R;
import com.chigov.retrofit.adapters.EmployeeAdapter;
import com.chigov.retrofit.pojo.Employee;
import com.chigov.retrofit.pojo.Speciality;

import java.util.ArrayList;
import java.util.List;


public class EmployeeListActivity extends AppCompatActivity{
    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;
    private EmployeeViewModel viewModel;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployees);
        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<>());
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);//устанавливаем список сотрудников
                // вывод в лог всех специальностей
                if (employees != null) {
                    for (Employee employee : employees) {
                        List<Speciality> specialities = employee.getSpecialty();
                        //выведем названия всех специальностей
                        for (Speciality speciality : specialities) {
                            Log.i("test", speciality.getName());
                            }
                    }
                }
            }
        });
        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable != null){
                Toast.makeText(EmployeeListActivity.this, "No Internet database access", Toast.LENGTH_SHORT).show();
                viewModel.clearErrors();
                }
            }
        });
        viewModel.loadData();
    }

}
