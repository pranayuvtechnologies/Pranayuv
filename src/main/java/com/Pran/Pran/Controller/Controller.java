package com.Pran.Pran.Controller;


import com.Pran.Pran.Mail.MailService;
import com.Pran.Pran.Model.*;
import com.Pran.Pran.Service.AdminService;
import com.Pran.Pran.Service.ContactMessageService;
import com.Pran.Pran.Service.ProductService;
import com.Pran.Pran.Service.ServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pran")
public class Controller {

    private ServiceRegister register;
    private ContactMessageService message;
    private MailService mailService;

    @Autowired
    private AdminService adminService;



    @Autowired
    private ProductService productService;

    @Autowired
    Controller(ServiceRegister register, ContactMessageService message, MailService mailService){
        this.register = register;
        this.message=message;
        this.mailService=mailService;

    }


    @PostMapping("/register")
    public void register(@RequestBody()Users user){
        register.addUser(user);

    }

    @PostMapping("/submittask/{username}")
    public void submitTask(@PathVariable("username") String username,@RequestParam("link") String link){
        adminService.addCompletedTaskToDb(username,link);
    }

    @PostMapping("/contact")
    public void contact(@RequestBody()Contact contact){
        message.addMessage(contact);
    }

    @GetMapping("/home")
    public String map(){
        return "hello all";
    }

    @PostMapping("/forgotpassword/{email}")
    public String forgotpassword(@PathVariable("email") String email){
        mailService.editPassword(email);
        return "";
    }

    @PostMapping("/otp/{otp}/{password}")
    public String otp(@PathVariable("otp") int otp, @PathVariable("password") String password,@RequestParam("username") String username){
        return register.checkOtpAndChangePassword(username,otp,password);

    }



    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK);
    }


    //---------------------------------

    @GetMapping("/getallcourses")
    public List<Course> getallcourses(){
        return register.getAllCourses();
    }

    @PostMapping("/adduserdetails")
    public void addUseToUserInfo(@RequestBody()UserInfo userInfo){
        register.addUserInfoToDb(userInfo);
    }


    @GetMapping("/getmytasks/{username}")
    public List<Task> getMyTas(@PathVariable("username") String username){
        return register.getmytask(username);
    }





}
