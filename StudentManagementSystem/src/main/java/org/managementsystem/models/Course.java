package org.managementsystem.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Course {

    private static final AtomicInteger counter = new AtomicInteger(100);
    private String courseName;
    private int courseId;

    public Course(){}

    public Course(String courseName){
        this.courseName = courseName;
        this.courseId   = counter.getAndIncrement();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}
