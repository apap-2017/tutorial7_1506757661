package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentMapper;
import com.example.model.CourseModel;
import com.example.model.StudentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceDatabase implements StudentService
{
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public StudentModel selectStudent (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectStudent (npm);
    }


    @Override
    public List<StudentModel> selectAllStudents ()
    {
        log.info ("select all students");
        return studentMapper.selectAllStudents ();
    }


    @Override
    public void addStudent (StudentModel student)
    {
        studentMapper.addStudent (student);
    }


    @Override
    public void deleteStudent (String npm)
    {
		log.info ("student" + npm + " deleted");
		studentMapper.deleteStudent(npm);
    }


	@Override
	public void updateStudent(StudentModel student) {
		// TODO Auto-generated method stub
		log.info("student updated");
		studentMapper.updateStudent(student);
	}


	@Override
	public CourseModel selectCourse(String id_course) {
		// TODO Auto-generated method stub
		 log.info ("select course with id_course {}", id_course);
	     return studentMapper.selectCourse (id_course);
		
	}


	@Override
	public String selectNama(String npm) {
		// TODO Auto-generated method stub
		 log.info ("select nama with npm {}", npm);
	     return studentMapper.selectNama (npm);
		
	}


	@Override
	public List<CourseModel> selectAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

}
