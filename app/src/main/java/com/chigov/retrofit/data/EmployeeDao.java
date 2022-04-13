package com.chigov.retrofit.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.chigov.retrofit.pojo.Employee;

import java.util.List;

@Dao
public interface  EmployeeDao {
    //первый объект -  это будет запрос к базе
    @Query("SELECT * FROM employees")// employees - это название БД
    LiveData<List<Employee>> getEmployees();

    //вносить данные в базу
    @Insert(onConflict = OnConflictStrategy.REPLACE)//для предотвращения падения
    void insertEmployees(List<Employee> employees);//вставлять будем целым списком

    //метод для удаления из базы
    @Query("DELETE FROM employees")
    void deleteAllEmployees();
}
