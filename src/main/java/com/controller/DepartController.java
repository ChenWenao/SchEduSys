package com.controller;

import com.bean.Department;
import com.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartController {
    @Autowired
    DepartService departService;

    @PostMapping("/SchEduSys/Depart/newDepart")
    public boolean addNewDepartment(@ModelAttribute(value = "Department")Department department){
        return departService.addNewDepartment(department);
    }
}
