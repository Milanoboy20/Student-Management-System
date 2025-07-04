package org.managementsystem;

import org.managementsystem.models.Course;
import org.managementsystem.models.Student;
import org.managementsystem.models.Teacher;

import java.util.ArrayList;
import java.util.List;

public class App {

    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private final AppHelper appHelper;

    public App(AppHelper appHelper) {
        this.appHelper = appHelper;
    }

    //application start
    public void run() {
        appHelper.displayHeader("This is the Student Management System.");
        
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
                case UPDATE_STUDENT: updateStudent();
                break;
                case UPDATE_TEACHER: updateTeacher();
                break;
                case DELETE_STUDENT: deleteStudent();
                break;
                case DELETE_TEACHER: deleteTeacher();
                break;
                case DELETE_COURSE: deleteCourse();
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

        //add to general students list
        students.add(student);
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

        //add to general students list
        teachers.add(teacher);
    }

    private void addCourse() {
        appHelper.displayHeader(MenuOption.ADD_COURSE.getMessage());

        //get course name
        String name = appHelper.getName();

        //create new course instance and add to course list
        Course course = new Course(name);

        courses.add(course);
    }

    private void assignStudentCourses() {
        appHelper.displayHeader(MenuOption.ASSIGN_STUDENT_COURSE.getMessage());

        //choose from list of students to assign course selected from available courses
        Student student;
        if (!students.isEmpty()) {
            student = appHelper.chooseStudent(students);
            if ((student == null || student.getStudentId() == 0)) {
                appHelper.displayStatus(false, "Student does not exist");
            }
            else {
                Course course;
                if (!courses.isEmpty()) {
                    course = appHelper.chooseCourse(courses);
                    if (course == null || course.getCourseId() == 0) {
                        appHelper.displayStatus(false, "Course does not exist");
                    }
                    else {
                        //add course to student courses list
                        student.addCourse(course);
                        //finally update student in student list
                        appHelper.updateStudent(student, students);
                    }
                } else
                    appHelper.displayStatus(false, "Cannot assign courses to Student. At least one Course should exist.");//if courses list is empty
            }
        } else
            appHelper.displayStatus(false, "Cannot assign courses to Students. At least one Student should exist.");// if students list is empty
    }

    private void assignTeacherCourses() {
        appHelper.displayHeader(MenuOption.ASSIGN_TEACHER_COURSE.getMessage());

        //choose teacher from list and assign course selected from available courses
        Teacher teacher;
        if (!teachers.isEmpty()) {
            teacher = appHelper.chooseTeacher(teachers);
            if (teacher == null || teacher.getTeacherId() == 0) {
                appHelper.displayStatus(false, "Teacher does not exist");
            }
            else {
                Course course;
                if (!courses.isEmpty()) {
                    course = appHelper.chooseCourse(courses);
                    if (course == null || course.getCourseId() == 0) {
                        appHelper.displayStatus(false, "Course does not exist");
                    }
                    else {
                        //add course to student courses list
                        teacher.addCourse(course);
                        //finally update student in student list
                        appHelper.updateTeacher(teacher, teachers);
                    }
                } else
                    appHelper.displayStatus(false, "Cannot assign courses to Teacher. At least one Course should exist.");//empty courses list
            }
        } else
            appHelper.displayStatus(false, "Cannot assign courses to Teachers. At least one Teacher should exist.");//empty teachers list

    }

    private void viewStudent() {
        appHelper.displayHeader(MenuOption.VIEW_STUDENT.getMessage());

        //can only view student if list is not empty
        if (!students.isEmpty()) {
            int studentId = appHelper.getId("Student");
            appHelper.displayStudent(appHelper.getStudent(students,studentId));
        }
        else appHelper.displayStatus(false, "Cannot view Student. At least one Student should exists.");
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


    private void updateStudent() {
        appHelper.displayHeader(MenuOption.UPDATE_STUDENT.getMessage());

        //check students list if empty
        if (!students.isEmpty()){
            //choose student to update from the list
            Student student = appHelper.chooseStudent(students);
            if (student != null && student.getStudentId() != 0){
                appHelper.getUpdateDetails(student);
                appHelper.updateStudent(student, students);
                appHelper.displayStatus(true,"Update successful.");
            } else appHelper.displayStatus(false,"Update failed. Student does not exist!");
        }
        else appHelper.displayStatus(false,"Cannot perform update operation. At least one Student should exist.");
    }

    private void updateTeacher() {
        appHelper.displayHeader(MenuOption.UPDATE_TEACHER.getMessage());

        //check teachers list if empty
        if (!teachers.isEmpty()) {
            //choose teacher to update from the list
            Teacher teacher = appHelper.chooseTeacher(teachers);
            if (teacher != null && teacher.getTeacherId() != 0){
                appHelper.getUpdateDetails(teacher);
                appHelper.updateTeacher(teacher, teachers);
                appHelper.displayStatus(true,"Update successful.");
            } else appHelper.displayStatus(false,"Update failed. Teacher does not exist!");
        }
        else appHelper.displayStatus(false, "Cannot perform update operation. At least one Teacher should exist.");

    }

    private void deleteStudent() {
        appHelper.displayHeader(MenuOption.DELETE_STUDENT.getMessage());

        //at least one student should exist in current list
        if (!students.isEmpty()) {
            //get student id
            int studentId = appHelper.getId("Student");

            //check if student exists
            boolean studentExists = students.stream().anyMatch(s -> s.getStudentId() == studentId);
            if (studentExists) {
                //delete student from students list
                List<Student> updatedStudentsList = appHelper.removeStudent(students, studentId);
                //set student list to new list without deleted student
                setStudents(updatedStudentsList);
                appHelper.displayStatus(true, "Delete successful.");
            } else
                appHelper.displayStatus(false,"Student does not exist.");
        }
        else appHelper.displayStatus(false, "Cannot perform delete operation. At least one Student should exist.");
    }

    private void setStudents(List<Student> students) {
        this.students = students;
    }

    private void deleteTeacher() {
        appHelper.displayHeader(MenuOption.DELETE_TEACHER.getMessage());

        //should have at least one teacher in current list
        if (!teachers.isEmpty()) {
            //get teacher id
            int teacherId = appHelper.getId("Teacher");

            //check if teacher exists
            boolean teacherExists = teachers.stream().anyMatch(t -> t.getTeacherId() == teacherId);
            if (teacherExists) {
                //delete teacher from teachers list
                List<Teacher> updatedTeachersList = appHelper.removeTeacher(teachers, teacherId);
                //set teacher list to new list without deleted teacher
                setTeachers(updatedTeachersList);
                appHelper.displayStatus(true, "Delete successful.");
            } else
                appHelper.displayStatus(false,"Teacher does not exist.");
        }
        else appHelper.displayStatus(false, "Cannot perform delete operation. At least one Teacher should exist.");
    }

    private void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    private void deleteCourse() {
        appHelper.displayHeader(MenuOption.DELETE_COURSE.getMessage());

        //at least one course should exist
        if (!courses.isEmpty()) {
            //get course id
            int courseId = appHelper.getId("Course");

            //check if course exists
            boolean courseExists = courses.stream().anyMatch(c -> c.getCourseId() == courseId);
            if (courseExists) {
                //first delete course registration by students and teachers
                Course courseToRemove = courses.stream().filter(c -> c.getCourseId() == courseId).findFirst().get();
                appHelper.updateStudentsAndTeachers(students, teachers, courseToRemove);

                //delete course from courses list
                List<Course> updatedCoursesList = appHelper.removeCourse(courses, courseId);
                //set courses to updated courses list
                setCourses(updatedCoursesList);
                appHelper.displayStatus(true, "Delete successful.");
            } else
                appHelper.displayStatus(false,"Course does not exist.");
        }
        else appHelper.displayStatus(false, "Cannot perform delete operation. At least one Course should exist.");
    }

    private void setCourses(List<Course> updatedCoursesList) {
        this.courses = updatedCoursesList;
    }
}
