package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.CourseModel;
import com.example.service.StudentService;

@RestController
@RequestMapping("/rest")
public class CourseRestController {
	
	@Autowired
	StudentService studentService;
	

    @RequestMapping("/course/view/{id}")
    public CourseModel viewCoursePath (Model model,
            @PathVariable(value = "id") String id)
    {
        CourseModel course = studentService.selectCourse (id);

        if (course != null) {
            model.addAttribute ("course", course);
            return course;
        } else {
            model.addAttribute ("id", id);
            return null;
        }
    }
    
    @RequestMapping("/course/viewall")
    public List<CourseModel> viewall (Model model)
    {

       List<CourseModel> courses = studentService.selectAllCourses ();
        model.addAttribute ("courses", courses);

        return courses;
       
    }
    
    

}
