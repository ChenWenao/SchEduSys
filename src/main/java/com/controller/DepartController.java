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
    //新建学院
    @PostMapping("Depart/newDepart")
    public boolean addNewDepartment(@ModelAttribute(value = "newDepartment") Department newDepartment) {
        return departService.addNewDepartment(newDepartment);
    }

    //删
    //删除学院
    @PostMapping("Depart/removeDepart")
    public boolean removeDepartment(@RequestBody List<String> departNames) {
        for (String departName : departNames) {
            if (departService.getDepartmentByName(departName) == null || !departService.removeDepartment(departName)) {
                return false;
            }
        }
        return true;
    }

    //改
    //下架学院
    @PostMapping("Depart/dropDepart")
    public boolean dropDepartment(@RequestBody List<String> departNames) {
        for (String departName : departNames) {
            if (departService.getDepartmentByName(departName) == null || !departService.dropDepartment(departName)) {
                return false;
            }
        }
        return true;
    }

    //上架学院
    @PostMapping("Depart/restoreDepart")
    public boolean restoreDepartment(@RequestBody List<String> departNames) {
        for (String departName : departNames) {
            if (departService.getDepartmentByName(departName) == null || !departService.restoreDepartment(departName)) {
                return false;
            }
        }
        return true;
    }

    //修改学院的全部信息
    @PostMapping("Depart/modifyDepart")
    public boolean modifyDepartment(@ModelAttribute(value = "Department") Department department) {
        Department department_find = departService.getDepartmentById(department.getDepartId());
        if (department_find == null)
            return false;//要修改的department不存在。
        return departService.modifyDepartment(department);
    }

    //查
    @GetMapping("Depart/departByName/{departName}")
    public Department getDepartmentById(@PathVariable("departName") String departName) {
        return departService.getDepartmentByName(departName);
    }

    @GetMapping("Depart/departById/{departId}")
    public Department getDepartmentById(@PathVariable("departId") int departId) {
        return departService.getDepartmentById(departId);
    }

    // order_by表示根据哪个字段查询，
    // order表示正序还是倒序查询，order为0表示逆序，1表示正序
    // isEnable表示是否启用，on表示查询启用的学院，off表示查询未启用的学院，all表示查询所有学院
    // page表示第几页，pageSize表示每页几条数据
    @GetMapping("Depart/departments/{isEnable}/{order_by}/{order}/{page}/{pageSize}")
    public List<Department> getDepartments(@PathVariable("isEnable") String isEnable, @PathVariable("order_by") String order_by, @PathVariable("order") String order, @PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {
        return departService.getDepartments(isEnable, order_by, order, page, pageSize);
    }
}
