package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index (Model headerTitle)
    {
    	headerTitle.addAttribute("title", "Home");
        return "index";
    }


    @RequestMapping("/student/add")
    public String add (Model headerTitle)
    {
    	headerTitle.addAttribute("title", "Add Student");
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa,
            Model headerTitle)
    {
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);
        headerTitle.addAttribute("title", "Success Add Student");

        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm, Model headerTitle)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            headerTitle.addAttribute("title", "View Student");

            return "view";
        } else {
            model.addAttribute ("npm", npm);
            headerTitle.addAttribute("title", "Not Found");
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm, Model headerTitle)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            headerTitle.addAttribute("title", "View Student");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            headerTitle.addAttribute("title", "Not Found");
            return "not-found";
        }
    }

    @RequestMapping("/course/view/{id}")
    public String viewCoursePath (Model model,
            @PathVariable(value = "id") String id, Model headerTitle)
    {
        CourseModel course = studentDAO.selectCourse (id);

        if (course != null) {
            model.addAttribute ("course", course);
            headerTitle.addAttribute("title", "View Course");
            return "viewcourse";
        } else {
            model.addAttribute ("id", id);
            headerTitle.addAttribute("title", "Not Found");
            return "course-not-found";
        }
    }    


    @RequestMapping("/student/viewall")
    public String view (Model model, Model headerTitle)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        headerTitle.addAttribute("title", "View All");
        model.addAttribute ("students", students);

        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm, Model headerTitle)
    {
    	  StudentModel student = studentDAO.selectStudent (npm);

          if (student != null) {
        	  studentDAO.deleteStudent (npm);
        	  headerTitle.addAttribute("title", "Delete Student");
              return "delete";} else {
              model.addAttribute ("npm", npm);
              headerTitle.addAttribute("title", "Not Found");
              return "not-found";
          }
          
        
    }
    
    @RequestMapping("/student/select/{npm}")
    public String selectNama (Model model, @PathVariable(value = "npm") String npm, Model headerTitle)
    {
    	  StudentModel student = studentDAO.selectStudent (npm);

          if (student != null) {
        	  String nama = studentDAO.selectNama (npm);
        	  model.addAttribute("nama", nama);
        	  headerTitle.addAttribute("title", "Select Student");
              return "nama";} 
          else {
              model.addAttribute ("npm", npm);
              headerTitle.addAttribute("title", "Not Found");
              return "not-found";
          }
          
        
    }

    /*
    
    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit (
    @RequestParam(value = "npm", required = false) String npm,
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "gpa", required = false) double gpa) {
    	 StudentModel student = new StudentModel (npm, name, gpa);

         studentDAO.updateStudent (student);
    	return "success-update";
    }

  
 *   
 */
    @RequestMapping("/student/update/{npm}")
    public String update (Model model, @PathVariable(value = "npm") String npm, Model headerTitle)
    {
    	  StudentModel student = studentDAO.selectStudent (npm);

          if (student != null) {
        	  model.addAttribute("student", student);
        	  headerTitle.addAttribute("title", "Update Student");
        	  return "form-update";
          }
          model.addAttribute ("npm", npm);
          headerTitle.addAttribute("title", "Not Found");
          return "not-found";
    }
    
    
    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit (@ModelAttribute StudentModel student, Model headerTitle) {
         studentDAO.updateStudent (student);
         headerTitle.addAttribute("title", "Success Update Student");
    	return "success-update";
    }


}
