package com.dao;

import com.bean.Course;
import com.bean.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ScheduleRepository {
    @Autowired
    private JdbcTemplate template;
    private ScheduleRowMapper scheduleRowMapper=new ScheduleRowMapper();

    //增
    public boolean insertANewSchedule(int sch_courseId,int sch_teacherId){
        try {
            //将课程置为有教师的状态。
            template.update("update Course set haveTeacher='T' where courseId=?",sch_courseId);
            //新建教师与课程的联系。
            template.update("insert into CourseSchedule(sch_courseId,sch_teacherId) values (?,?)",sch_courseId,sch_teacherId);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //删
    public boolean deleteSchedule(int sch_courseId){
        try {
            //删除Register表的数据。
            template.update("delete from CourseRegister where reg_courseId=?",sch_courseId);
            //删除Schedule表的数据。
            template.update("delete from CourseSchedule where sch_courseId=?",sch_courseId);
            //将课程置为没有教师的状态。
            template.update("update Course set haveTeacher='F' where courseId=?", sch_courseId);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //改
    public boolean dropSchedule(int sch_courseId){
        try {
            //下架Register表的数据。
            template.update("update CourseRegister set isEnable='F' where reg_courseId=?",sch_courseId);
            //下架Schedule表的数据。
            template.update("update CourseSchedule set isEnable='F' where sch_courseId=?",sch_courseId);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean restoreSchedule(int sch_courseId){
        try {
            //恢复Register表的数据。
            template.update("update CourseRegister set isEnable='T' where reg_courseId=?",sch_courseId);
            //恢复Schedule表的数据。
            template.update("update CourseSchedule set isEnable='T' where sch_courseId=?",sch_courseId);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean publishScore(int courseId){
        try {
            template.update("update courseSchedule set publishScore='T' where sch_courseId=?",courseId);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean publishScoreOff(int courseId){
        try {
            template.update("update courseSchedule set publishScore='F' where sch_courseId=?",courseId);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //查
    public Schedule selectScheduleByCourseId(int courseId){
        try {

            List<Schedule> schedules=template.query("select * from Course,Teacher,courseSchedule where courseId = sch_courseId and teacherId = sch_teacherId and courseId=?"
                    ,scheduleRowMapper,courseId);
            return schedules.get(0);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<Schedule> selectScheduleByTeacherId(String teacherCode){
        try {

            List<Schedule> schedules=template.query("select * from Course,Teacher,courseSchedule where courseId = sch_courseId and teacherId = sch_teacherId and courseSchedule.isEnable='T' and teacherCode=? "
                    ,scheduleRowMapper,teacherCode);
            return schedules;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }


    public List<Schedule> selectSchedules(String isEnable,String order_by,String order){
        try {
            String sql="select * from Course,Teacher,courseSchedule " +
                    "where courseId=sch_courseId " +
                    "and teacherId=sch_teacherId ";
            if("on".equals(isEnable))
                sql+="and courseSchedule.isEnable='T' ";
            else if("off".equals(isEnable))
                sql+="and courseSchedule.isEnable='F' ";
            sql+="order by ";
            sql+=order_by;
            if("0".equals(order))
                sql+=" desc";
            List<Schedule> schedules=template.query(sql,scheduleRowMapper);
            return schedules;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

}
