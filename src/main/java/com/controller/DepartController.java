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
    @PostMapping("/SchEduSys/Depart/newDepart")
    public boolean addNewDepartment(@ModelAttribute(value = "Department") Department department) {
        return departService.addNewDepartment(department);
    }

    //删

    //删除学院
    @RequestMapping("/SchEduSys/Depart/removeDepart/{departName}")
    public boolean removeDepartment(@PathVariable("departName") String departName) {
        if (departService.getDepartmentByName(departName) != null) {
            return departService.removeDepartment(departName);
        }
        return false;
    }

    //改

    //下架学院
    @RequestMapping("/SchEduSys/Depart/dropDepart/{departName}")
    public boolean dropDepartment(@PathVariable("departName") String departName) {
        if (departService.getDepartmentByName(departName) != null) {
            return departService.dropDepartment(departName);
        }
        return false;
    }

    //上架学院
    @RequestMapping("/SchEduSys/Depart/restoreDepart/{departName}")
    public boolean restoreDepartment(@PathVariable("departName") String departName){
        if (departService.getDepartmentByName(departName) != null) {
            return departService.restoreDepartment(departName);
        }
        return false;
    }

    //修改学院的全部信息
    @RequestMapping("/SchEduSys/Depart/modifyDepart/")
    public boolean modifyDepartment(@ModelAttribute(value = "Department") Department department){
        Department department_find=departService.getDepartmentById(department.getDepartId());
        if(department_find==null)
            return false;//要修改的department不存在。
        return departService.modifyDepartment(department);
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

    //order_by表示根据哪个字段查询，
    // order表示正序还是倒序查询，order为0表示逆序，其他值表示正序
    // isEnable表示是否启用，on表示查询启用的学院，off表示查询未启用的学院，all表示查询所有学院
    @RequestMapping("/SchEduSys/Depart/departments/{isEnable}/{order_by}/{order}")
    public List<Department> getAllDepartments(@PathVariable("isEnable")String isEnable,@PathVariable("order_by") String order_by, @PathVariable("order") String order) {
        return departService.getAllDepartments(order_by, order);
    }
}
