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
    public boolean addNewDepartment(Department newDepartment) {
        return departRepository.insertANewDepart(newDepartment);
    }

    //删
    public boolean removeDepartment(String departName) {
        return departRepository.deleteDepart(departName);
    }

    //改
    public boolean dropDepartment(String departName) {
        return departRepository.dropDepart(departName);
    }

    public boolean restoreDepartment(String departName) {
        return departRepository.restoreDepart(departName);
    }

    public boolean modifyDepartment(Department department) {
        return departRepository.modifyDepart(department);
    }

    //查
    public Department getDepartmentByName(String departName) {
        return departRepository.selectDepartByName(departName);
    }

    public Department getDepartmentById(int departId) {
        return departRepository.selectDepartById(departId);
    }

    public List<Department> getDepartments(String isEnable, String order_by, String order,int page,int pageSize) {
        return departRepository.selectDeparts(isEnable, order_by, order,page,pageSize);
    }
}
