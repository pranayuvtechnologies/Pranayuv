package com.Pran.Pran.Model;


import org.springframework.stereotype.Component;

@Component
public class CourseIdCat {

    private int courseId;
    private String category;


    public CourseIdCat(int courseId, String category) {
        this.courseId = courseId;
        this.category = category;
    }

    public CourseIdCat() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}