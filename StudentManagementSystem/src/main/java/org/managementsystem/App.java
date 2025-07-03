package org.managementsystem;

import org.managementsystem.models.Course;
import org.managementsystem.models.Student;
import org.managementsystem.models.Teacher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App {

    private static final List<Student> students = new ArrayList<>();
    private static final List<Teacher> teachers = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();
    private final AppHelper appHelper;
    private final UserPromptHelper promptHelper;

    public App(AppHelper appHelper, UserPromptHelper promptHelper) {
        this.appHelper = appHelper;
        this.promptHelper = promptHelper;
    }

    public static void main(String[] args) {
        UserPromptHelper promptHelper = new UserPromptHelper();
        AppHelper appHelper1 = new AppHelper(promptHelper);
        App app = new App(appHelper1, promptHelper);
        app.run();

    }

    //Program functions/methods
    public void run() {
        appHelper.displayHeader("Welcome to Student Management System.");
        
        try {
            runAppLoop();
        } catch (Exception ex) {
            appHelper.displayHeader("CRITICAL ERROR: " + ex.getMessage());
        }
        
        appHelper.displayHeader("GoodBye!");
    }
    
    private void runAppLoop() throws Exception {
        MenuOption option;
        //will run and loop menu options until user selects exit
        do {
            option = appHelper.selectMainMenuOption();
            switch (option) {
                case ADD_STUDENT: addStudent();
                break;
                case ADD_TEACHER: addTeacher();
                break;
                case ADD_COURSE: addCourse();
                break;
                case ASSIGN_STUDENT_COURSE: assignStudentCourses();
                break;
                case ASSIGN_TEACHER_COURSE: assignTeacherCourses();
                break;
                case VIEW_STUDENT: viewStudent();
                break;
                case VIEW_STUDENTS: viewStudents();
                break;
                case VIEW_TEACHERS: viewTeachers();
                break;
                case VIEW_COURSES: viewCourses();
                break;
                case VIEW_ALL: viewAll();
                break;
                case UPDATE_STUDENT: updateStudent();
                break;
                case DELETE_STUDENT: deleteStudent();
                break;
            }
        } while (option != MenuOption.EXIT);
    }

    private void addStudent() {
        appHelper.displayHeader(MenuOption.ADD_STUDENT.getMessage());

        //get student name
        String name = appHelper.getName();

        //get student age
        int age = appHelper.getAge();

        //get email
        String email = appHelper.getEmail();

        //get grade level -> freshman(1), sophomore(2), junior(3), senior(4)
        int grade = appHelper.getGrade();

        //create new student instance
        Student student = new Student(name, email, age,grade);
        System.out.println(student);

        //add to general students list
        students.add(student);

        System.out.println("All students: \n" + students);
    }




    private void addTeacher() {
        appHelper.displayHeader(MenuOption.ADD_TEACHER.getMessage());
        //get teacher name
        String name = appHelper.getName();

        //get teacher age
        int age = appHelper.getAge();

        //get email
        String email = appHelper.getEmail();

        //get subject
        String subject = appHelper.getSubject();

        //create new student instance
        Teacher teacher = new Teacher(name, email, age, subject);
        System.out.println(teacher);

        //add to general students list
        teachers.add(teacher);

        System.out.println("All teachers: \n" + teachers);
    }

    private void addCourse() {
        appHelper.displayHeader(MenuOption.ADD_COURSE.getMessage());

        //get course name
        String name = appHelper.getName();

        //create new course instance and add to course list
        Course course = new Course(name);

        courses.add(course);
        System.out.println("All courses: \n" + courses);
    }

    private void assignStudentCourses() {
        appHelper.displayHeader(MenuOption.ASSIGN_STUDENT_COURSE.getMessage());

        //choose from list of students to assign course
        Student student = appHelper.chooseStudent(students);
        Course course = appHelper.chooseCourse(courses);
        student.addCourse(course);
    }

    private void assignTeacherCourses() {
        appHelper.displayHeader(MenuOption.ASSIGN_TEACHER_COURSE.getMessage());

        //choose teacher from list and assign course
        Teacher teacher = appHelper.chooseTeacher(teachers);
        Course course = appHelper.chooseCourse(courses);
        teacher.addCourse(course);
    }

    private void viewStudent() {
        appHelper.displayHeader(MenuOption.VIEW_STUDENT.getMessage());

        int studentId = appHelper.getStudentId();
        appHelper.displayStudent(appHelper.getStudent(students,studentId));
    }

    private void viewStudents() {
        appHelper.displayHeader(MenuOption.VIEW_STUDENTS.getMessage());

        appHelper.displayStudents(students);
    }

    private void viewTeachers() {
        appHelper.displayHeader(MenuOption.VIEW_TEACHERS.getMessage());

        appHelper.displayTeachers(teachers);
    }

    private void viewCourses() {
        appHelper.displayHeader(MenuOption.VIEW_COURSES.getMessage());

        appHelper.displayCourses(courses);
    }

    private void viewAll() {
    }

    private void updateStudent() {
    }

    private void deleteStudent() {
    }
    
}
