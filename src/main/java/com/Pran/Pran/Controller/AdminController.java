package com.Pran.Pran.Controller;

import com.Pran.Pran.Model.*;
import com.Pran.Pran.PromotionalMails;
import com.Pran.Pran.Repo.MessageRepo;
import com.Pran.Pran.Service.AdminService;
import com.Pran.Pran.Service.ContactMessageService;
import com.Pran.Pran.Service.ServiceRegister;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ServiceRegister register;
    private final MessageRepo messageRepo;
    private final AdminService adminService;
    @Autowired
    AdminController(ServiceRegister register, MessageRepo message, AdminService adminService){
        this.register = register;
        this.messageRepo=message;
        this.adminService=adminService;
    }




    
    @GetMapping("/getmessages")
    public List<Contact> contacts(){
        return messageRepo.findAll();
    }

    @PostMapping("/adminregister")
    public void register(@RequestBody() Users user){
        register.addAdmin(user);

    }


    //-------------//courses//-----------------------//

    //----remember while adding new values to database there is no need to send id values////

    @GetMapping("/getallcourses")
    public List<Course> getallcourses(){
        return adminService.getAllCourses();
    }

    @PostMapping("/addcourse")
    public void addCourse(@RequestBody() Course course){
        adminService.addCourseToDb(course);
    }

    @DeleteMapping("/deletecourse/{id}")
    public void deleteCourse(@PathVariable("id") int id){
        adminService.deleteCourseFromDb(id);
    }

    @PutMapping("/updatecourse")
    public void updateCourse(@RequestBody() Course course){
        adminService.updateCourse(course);
    }

    //-----------------UserInfo---------------//

    //only admin get all the details Only Reading//

    @GetMapping("/getuserinfo")
    public List<UserInfo> getAllUser(){
        return adminService.getALlUserInfo();
    }

    ///---------------tasks--------------------//
    // if we want to insert a task first course_id must be in courses table
    @PostMapping("/addtask")
    public void addTask(@RequestBody()Task task){
        adminService.addTaskToDb(task);
    }

    @PutMapping("/deletetask/{id}")
    public void deleteTask(@PathVariable("id") int id){
        adminService.deleteTaskFromDb(id);
    }

    @PostMapping("/submittask/{username}")
    public void submitTask(@PathVariable("username") String username,@RequestParam("link") String link){
        adminService.addCompletedTaskToDb(username,link);
    }

    @GetMapping("/getsubmits")
    public List<TasksCompletedModel> completedModel(){
        return adminService.getSubmittedTasks();
    }

    @GetMapping("/getsubmitbyusername/{username}")
    public String linktogithub(@PathVariable("username") String username){
        return adminService.getlinkByusername(username);
    }

    @GetMapping("/getalltasks")
    public List<Task> getAllTasks(){
        return adminService.getalltasks();
    }

    @GetMapping("/getcourseidcat")
    public List<CourseIdCat> getcourseIdcat(){
        return adminService.getcourseidcat();
    }
    @DeleteMapping("/deleteallcompletedtasks")
    public void deleteAllCompletedTasks() {
        adminService.truncateCompletedTasks();
    }
    @DeleteMapping("/deleteusersbycategory/{category}")
    public void deleteUsersByCategory(@PathVariable("category") String category) {
        adminService.deleteUsersByCategory(category);
    }
    @DeleteMapping("/deletetasksbycategory/{category}")
    public void deleteTasksByCategory(@PathVariable("category") String category) {
        adminService.deleteTasksByCategory(category);
    }
    @DeleteMapping("/deletesubmitsbyusername/{username}")
    public void deleteSubmitsByUsername(@PathVariable("username") String username) {
        adminService.deleteSubmitsByUsername(username);
    }


}
