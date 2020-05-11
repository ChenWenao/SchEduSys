package com.dao;

import com.bean.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartRepository {
    @Autowired
    private JdbcTemplate template;
    DepartRowMapper departRowMapper = new DepartRowMapper();

    //增
    public boolean insertANewDepart(Department newDepartment) {
        try {
            template.update("insert into Department(departName,departCreateTime,departDescription) values (?,?,?)"
                    , newDepartment.getDepartName()
                    , newDepartment.getDepartCreateTime()
                    , newDepartment.getDepartDescription());
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //删
    public boolean deleteDepart(String departName) {
        try {
            //删除CourseRegister
            template.update("delete from courseRegister where reg_courseId in (select courseId from Course where courseDepartName=?)", departName);
            //删除CourseSchedule
            template.update("delete from courseSchedule where sch_courseId in (select courseId from Course where courseDepartName=?)", departName);
            //删除Course
            template.update("delete from Course where departName=?", departName);
            //删除Department
            template.update("delete from Department where departName=?", departName);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //改
    //软删除
    public boolean dropDepart(String departName) {
        try {
            //删除CourseRegister
            template.update("update courseRegister set isEnable='F' where reg_courseId in (select courseId from Course where courseDepartName=?)", departName);
            //删除CourseSchedule
            template.update("update courseschedule set isEnable='F' where sch_courseId in (select courseId from Course where courseDepartName=?)", departName);
            //删除Course
            template.update("update Course set isEnable='F' where coursedepartName=?", departName);
            //删除学院。
            template.update("update Department set isEnable='F' where departName=?", departName);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean restoreDepart(String departName) {
        try {
            //恢复CourseRegister
            template.update("update courseRegister set isEnable='T' where reg_courseId in (select courseId from Course where courseDepartName=?)", departName);
            //恢复CourseSchedule
            template.update("update courseschedule set isEnable='T' where sch_courseId in (select courseId from Course where courseDepartName=?)", departName);
            //恢复Course
            template.update("update Course set isEnable='T' where coursedepartName=?", departName);
            //恢复学院。
            template.update("update Department set isEnable='T' where departName=?", departName);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean modifyDepart(Department department) {
        try {
            //修改Department
            template.update("update Department set departName=?,departCreateTime=?,departDescription=? where departId=?"
                    , department.getDepartName(), department.getDepartCreateTime(), department.getDepartDescription(), department.getDepartId());
            //修改Course表
            template.update("update Course set courseDepartName=? where courseDepartId=?", department.getDepartName(), department.getDepartId());
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //查
    public Department selectDepartByName(String departName) {
        try {
            List<Department> departments = template.query("select * from Department where departName=?", departRowMapper, departName);
            return departments.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Department selectDepartById(int departId) {
        try {
            List<Department> departments = template.query("select * from Department where departId=?", departRowMapper, departId);
            return departments.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Department> selectDeparts(String isEnable, String order_by, String order, int page, int pageSize) {
        try {
            String sql = "select * from Department ";
            if ("on".equals(isEnable))
                sql += "where isEnable='T' order by ";
            else if ("off".equals(isEnable))
                sql += "where isEnable='F' order by ";
            else
                sql += "order by ";
            sql += order_by;
            if ("0".equals(order))
                sql += " desc";
            if (page != 0 || pageSize != 0)
                sql += " limit " + (page - 1) * pageSize + "," + pageSize;
            List<Department> departments = template.query(sql, departRowMapper);
            return departments;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
