package com.controller;

import com.bean.Department;
import com.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartController {
    @Autowired
    private DepartService departService;

    //增
    @PostMapping("/SchEduSys/Depart/newDepart")
    public boolean addNewDepartment(@ModelAttribute(value = "Department") Department department) {
        return departService.addNewDepartment(department);
    }

    //删
    @RequestMapping("/SchEduSys/Depart/removeDepart/{departName}")
    public boolean removeDepartment(@PathVariable("departName") String departName) {
        if (departService.getDepartmentByName(departName) != null) {
            return departService.removeDepartment(departName);
        }
        return false;
    }

    //改
    @RequestMapping("/SchEduSys/Depart/dropDepart/{departName}")
    public boolean dropDepartment(@PathVariable("departName") String departName) {
        if (departService.getDepartmentByName(departName) != null) {
            return departService.dropDepartment(departName);
        }
        return false;
    }

    //查
    @RequestMapping("/SchEduSys/Depart/departByName/{departName}")
    public Department getDepartmentById(@PathVariable("departName") String departName) {
        return departService.getDepartmentByName(departName);
    }

    @RequestMapping("/SchEduSys/Depart/departById/{departId}")
    public Department getDepartmentById(@PathVariable("departId") int departId) {
        return departService.getDepartmentById(departId);
    }

    @RequestMapping("/SchEduSys/Depart/departments/{order_by}/{order}")
    public List<Department> getAllDepartments(@PathVariable("order_by") String order_by, @PathVariable("order") String order) {
        return departService.getAllDepartments(order_by, order);
    }
}
