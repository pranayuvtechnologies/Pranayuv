package com.Pran.Pran.Service;


import com.Pran.Pran.Model.Course;
import com.Pran.Pran.Model.Task;
import com.Pran.Pran.Model.UserInfo;
import com.Pran.Pran.Model.Users;
import com.Pran.Pran.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRegister {


    private RegisterRepo repo;
    private OtpRepo otpRepo;

    @Autowired
    private TasksRepo  tasksRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    ServiceRegister(RegisterRepo repo,OtpRepo otpRepo){
        this.repo = repo;
        this.otpRepo=otpRepo;
    }
    public void addUser(Users user) {
        repo.addUserToDb(user);
    }

    public void addAdmin(Users user) {
        repo.addAdminToDb(user);
    }
    public List<Course> getAllCourses() {
        return courseRepo.getAllCourses();
    }

    public String checkOtpAndChangePassword(String username,int otp, String password) {
        boolean validatedOtp = otpRepo.checkOtp(username,otp,password);
        if(validatedOtp){
            repo.changepass(username,password);
            repo.deleteRecordFromOTP(username);
            return "";
        }
        else{
            repo.deleteRecordFromOTP(username);
            return "something went wrong";
        }
    }

    public void addUserInfoToDb(UserInfo userInfo) {
        userInfoRepo.addUser(userInfo);
    }

    public List<Task> getmytask(String username) {
        return tasksRepo.findByUsername(username);
    }
}
