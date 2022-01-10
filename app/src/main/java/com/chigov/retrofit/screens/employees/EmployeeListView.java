package com.chigov.retrofit.screens.employees;

import com.chigov.retrofit.pojo.Employee;

import java.util.List;

public interface EmployeeListView {
    void showData(List<Employee> employees);
    void showError();
}
