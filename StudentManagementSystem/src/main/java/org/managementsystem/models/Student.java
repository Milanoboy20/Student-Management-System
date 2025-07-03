package org.managementsystem.models;

import org.managementsystem.models.Course;
import org.managementsystem.models.Person;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

    private int studentId, grade;

    private final ArrayList<Course> courses = new ArrayList<>();

    public Student(String name, String email, int age, int grade, int studentId) {
        super(name, email, age);
        this.grade      = grade;
        this.studentId  = studentId;
    }

    public void addCourse(Course course) {
        // Student registered courses max is 5
        if (courses.size() == 5){
            System.out.println("Cannot add another course! Maximum course registrations reached.");
        }
        else courses.add(course);
    }

    public List<Course> getCourse() {
        return courses;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
