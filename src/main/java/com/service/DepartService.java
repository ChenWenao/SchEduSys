package com.service;

import com.bean.Department;
import com.dao.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartService {
    @Autowired
    private DepartRepository departRepository;

    //增
    public boolean addNewDepartment(Department newDepartment){
        return departRepository.insertANewDepart(newDepartment);
    }

    //删
    public boolean removeDepartment(String departName){
        return departRepository.deleteDepart(departName);
    }

    //改
    public boolean dropDepartment(String departName){
        return departRepository.dropDepart(departName);
    }

    //查
    public Department getDepartmentByName(String departName){
        return departRepository.selectDepartByName(departName);
    }

    public Department getDepartmentById(int departId){
        return departRepository.selectDepartById(departId);
    }

    public List<Department> getAllDepartments(String order_by,String order){
        return departRepository.selectAllDeparts(order_by,order);
    }


}
