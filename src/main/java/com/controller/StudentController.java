package com.controller;


import com.bean.Department;
import com.bean.Student;
import com.bean.User;
import com.service.DepartService;
import com.service.StudentService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class StudentController{
    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartService departService;

    //增
    //传入字段：userIdCard,userRealName       PS:isEnable默认是T，启用状态，密码默认123456，用户自己修改。
    //studentDepartName,studentGender,studentNativePlace,studentPoliticsStatus,studentPhoneNumber,studentNote
    //Ps:studentEntryTime不用管，数据库默认插入新建用户的时间。
    @PostMapping("Student/newStudent")
    public String addNewStudent(@ModelAttribute(value = "newStudent") Student newStudent, @ModelAttribute(value="newUser") User newUser) {
        //将user的数据同步到Student
        newStudent.setStudentIdCard(newUser.getUserIdCard());
        newStudent.setStudentRealName(newUser.getUserRealName());
        newUser.setUserIdentity("学生");
        //获取学院id
        Department department_find=departService.getDepartmentByName(newStudent.getStudentDepartName());
        if(department_find==null)
            return "学院不存在！";
        newStudent.setStudentDepartId(department_find.getDepartId());

        //生成学号
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        String code=formatter.format(new Date(System.currentTimeMillis()));//code开头为日期
        code+=newStudent.getStudentIdCard().substring(newStudent.getStudentIdCard().length()-8);//code接下来为身份证后八位
        if("男".equals(newStudent.getStudentGender()))
            code+="0";
        else if("女".equals(newStudent.getStudentGender()))
            code+="1";

        //管理员尾数为0，教师尾数为1，学生尾数为2
        if("管理员".equals(newUser.getUserIdentity()))
            code+="0";
        else if ("教师".equals(newUser.getUserIdentity()))
            code+="1";
        else
            code+="2";
        newUser.setUserCode(code);
        newStudent.setStudentCode(code);
        if(userService.addNewUser(newUser)) {
            if (studentService.addNewStudent(newStudent))
                return "学生创建成功！";
            else
                userService.removeUserByCode(newUser.getUserCode());
        }
        return "用户创建失败！";
    }

    //删
    //真删
    @PostMapping("Student/removeStudent")
    public boolean removeStudent(@RequestBody List<Integer> studentIds) {
        for (int studentId : studentIds) {
            if (!studentService.removeStudent(studentId)) {
                return false;
            }
        }
        return true;
    }

    //改
    //软删
    @PostMapping("Student/dropStudent")
    public boolean dropStudent(@RequestBody List<Integer> studentIds) {
        for (int studentId : studentIds) {
            if (studentService.getStudentById(studentId) == null || !studentService.dropStudent(studentId)) {
                return false;
            }
        }
        return true;
    }

    //恢复
    @PostMapping("Student/restoreStudent")
    public boolean restoreStudent(@RequestBody List<Integer> studentIds) {
        for (int studentId : studentIds) {
            if (studentService.getStudentById(studentId) == null || !studentService.restoreStudent(studentId)) {
                return false;
            }
        }
        return true;
    }
    //修改学生信息
    //传入的modifyStudent表单，需要包含以下字段：studentId，studentDepartName，studentGender，studentNativePlace，studentPoliticsStatus
    //，studentPhoneNumber，studentRealName，studentNote
    //也就是说，只能修改以上字段
    @PostMapping("Student/modifyStudent")
    public String modifyStudent(@ModelAttribute(value = "modifyStudent") Student modifyStudent) {
        Student student_find = studentService.getStudentById(modifyStudent.getStudentId());
        if (student_find == null)
            return "学生不存在！";//要修改的student不存在。
        Department department_find=departService.getDepartmentByName(modifyStudent.getStudentDepartName());
        if(department_find==null){
            return "要修改的学院不存在！";
        }
        modifyStudent.setStudentDepartId(department_find.getDepartId());
        if(studentService.modifyStudent(modifyStudent))
            return "修改成功！";
        return "修改失败！";
    }

    //查
    @GetMapping("Student/studentById/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId){
        return studentService.getStudentById(studentId);
    }

    @GetMapping("Student/studentByCode/{studentCode}")
    public Student getStudentByCode(@PathVariable("studentCode")String studentCode){
        return studentService.getStudentByCode(studentCode);
    }

    //批量查询
    //查询所有学生
    // isEnable表示是否启用，on表示查询启用的学生，off表示查询未启用的学生，all表示查询所有
    // order_by表示根据哪个字段查询
    // order表示正序还是倒序查询，order为0表示逆序，1表示正序
    @GetMapping("Student/students/{isEnable}/{order_by}/{order}")
    public List<Student> getStudents(@PathVariable("isEnable") String isEnable,@PathVariable("order_by")String order_by,@PathVariable("order")String order){
        return studentService.getStudents(isEnable, order_by, order);
    }

}