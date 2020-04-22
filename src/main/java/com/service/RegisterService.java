package com.service;

import com.dao.RegisterRepository;
import com.dao.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository;

    public boolean addNewRegister(int reg_teacherId,int reg_studentId,int reg_courseId){
        return registerRepository.insertANewRegister(reg_teacherId,reg_studentId,reg_courseId);
    }


}
