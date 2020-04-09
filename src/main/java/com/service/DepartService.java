package com.service;

import com.bean.Department;
import com.dao.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartService {
    @Autowired
    DepartRepository departRepository;

    //增
    public boolean addNewDepartment(Department newDepartment){
        return departRepository.insertANewDepart(newDepartment);
    }
}
