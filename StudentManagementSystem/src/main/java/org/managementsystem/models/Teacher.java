package org.managementsystem.models;

import org.managementsystem.models.Course;
import org.managementsystem.models.Person;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person {

    private int teacherId;
    private String subject;

    private final ArrayList<Course> courses = new ArrayList<>();

    public Teacher(String name, String email, int age, String subject, int teacherId) {
        super(name, email, age);
        this.subject    = subject;
        this.teacherId  = teacherId;
    }

    public void addCourse(Course course) {
        // Teachers can teach up to 3 courses only
        if (courses.size() == 3){
            System.out.println("Cannot teach another course! Maximum teachable courses reached.");
        }
        else courses.add(course);
    }

    public List<Course> getCourse() {
        return courses;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
