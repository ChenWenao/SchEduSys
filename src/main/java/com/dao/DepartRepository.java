package com.dao;

import com.bean.Department;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
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
    public boolean deleteDepart(String departName){
        try{
            template.update("delete from Department where departName=?",departName);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //改
    //软删除
    public boolean dropDepart(String departName) {
        try {
            //删除课程。
            template.update("update Course set isEnable='F' where courseDepartName=?", departName);
            //删除学院。
            template.update("update Department set isEnable='F' where departName=?", departName);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //查
    public Department selectDepartByName(String departName){
        try{
            List<Department> departments=template.query("select * from Department where departName=?",departRowMapper,departName);
            return departments.get(0);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public Department selectDepartById(int departId){
        try{
            List<Department> departments=template.query("select * from Department where departId=?",departRowMapper,departId);
            return departments.get(0);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<Department> selectAllDeparts(String order_by,String order){
        try {
            String sql="select * from Department order by "+order_by;
            if("0".equals(order))
                sql+=" desc";
            List<Department> departments=template.query(sql,departRowMapper);
            return departments;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
