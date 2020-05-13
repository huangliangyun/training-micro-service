package com.javahly.basicinfoservice.service.impl;

import com.javahly.basicinfoservice.dao.StudentDao;
import com.javahly.basicinfoservice.entity.Student;
import com.javahly.basicinfoservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/11
 * @QQ :1136513099
 * @desc :
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Override
    public List<Student> getStudents() {
        return studentDao.getStudents();
    }

    @Override
    public Student getStudent(String sId) {
        return studentDao.getStudent(sId);
    }

    @Override
    public List<Student> getStudentByIds(List<String> studentIds) {
        return studentDao.getStudentByIds(studentIds);
    }

    @Override
    public List<String> getStudentByTeacherClass(String tId) {
        return studentDao.getStudentByTeacherClass(tId);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }
}
