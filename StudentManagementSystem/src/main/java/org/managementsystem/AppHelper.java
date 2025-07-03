package org.managementsystem;

import org.managementsystem.models.Course;
import org.managementsystem.models.Student;
import org.managementsystem.models.Teacher;

import java.util.List;

public class AppHelper {

    private final UserPromptHelper promptHelper;
    public AppHelper(UserPromptHelper promptHelper) {this.promptHelper = promptHelper;}

    public MenuOption selectMainMenuOption() {
        displayHeader("Main Menu");
        int min = 0, max = 0;

        for (MenuOption option : MenuOption.values()) {
            promptHelper.printf("%s. %s%n", option.getValue(), option.getMessage());
            min = Math.min(min, option.getValue());
            max = Math.max(max, option.getValue());
        }

        String message = String.format("Select [%s-%s]: ", min,max);
        return MenuOption.getMenu(promptHelper.readInt(message, min, max));
    }

    public void displayHeader(String message) {
        promptHelper.println("");
        promptHelper.println(message);

        StringBuilder separator = new StringBuilder("=");
        //Append up to message length
        for (int i = 0; i < message.length(); i++)
            separator.append("=");

        promptHelper.println(separator.toString());
    }

    public void displayStudents(List<Student> students) {
        promptHelper.println("");
        if (students == null || students.isEmpty()) {
            promptHelper.println("No students registered/found.");
            return;
        }

        String format = "%-5s %-20s %-10s %-35s %-15s %-15s%n";
        promptHelper.printf(format,"ID","Student Name", "Age","Email","Grade","Courses");
        for (Student student : students) {
            promptHelper.printf(format,
                    student.getStudentId(),
                    student.getName(),
                    student.getAge(),
                    student.getEmail(),
                    getGradeLevel(student.getGrade()),
                    student.getCourse());
        }
    }

    public void displayStudent(Student student) {
        promptHelper.println("");
        if (student == null) {
            promptHelper.println("No student found.");
            return;
        }

        String format = "%-5s %-20s %-10s %-35s %-15s %-15s%n";
        promptHelper.printf(format,"ID","Student Name", "Age","Email","Grade","Courses");
        promptHelper.printf(format,
                student.getStudentId(),
                student.getName(),
                student.getAge(),
                student.getEmail(),
                getGradeLevel(student.getGrade()),
                student.getCourse());
    }

    public Student getStudent(List<Student> students, int studentId) {
        return students.stream().filter(s -> s.getStudentId() == studentId).findFirst().orElse(null);
    }

    public void displayTeachers(List<Teacher> teachers){
        promptHelper.println("");
        if (teachers == null || teachers.isEmpty()) {
            promptHelper.println("No Teachers found.");
            return;
        }

        String format = "%-5s %-20s %-10s %-35s %-15s %-15s%n";
        promptHelper.printf(format,"ID","Teacher Name", "Age","Email","Subject","Courses");
        for (Teacher teacher : teachers) {
            promptHelper.printf(format,
                    teacher.getTeacherId(),
                    teacher.getName(),
                    teacher.getAge(),
                    teacher.getEmail(),
                    teacher.getSubject(),
                    teacher.getCourse());
        }
    }

    public void displayCourses(List<Course> courses) {
        promptHelper.println("");
        if (courses == null || courses.isEmpty()) {
            promptHelper.println("No Courses found");
            return;
        }

        String format = "%-5s %-15s";
        promptHelper.printf(format, "ID","Course Name");
        for(Course course : courses) {
            promptHelper.printf(format,
                    course.getCourseId(),
                    course.getCourseName());
        }
    }


    public Student chooseStudent(List<Student> students) {
        promptHelper.println("");
        displayStudents(students);
        if (students == null || students.isEmpty()) return new Student();
        promptHelper.println("");

        int input;
        do {
            input = promptHelper.readInt("Student ID: ");
            if (input <= 0){
                displayStatus(false, "ID should be > 0");
            }
        } while (input <= 0);

        int finalInput = input;
        return students.stream().filter(s -> s.getStudentId() == finalInput).findFirst().orElse(null);
    }

    public Teacher chooseTeacher(List<Teacher> teachers) {
        promptHelper.println("");
        displayTeachers(teachers);
        if (teachers == null || teachers.isEmpty()) return new Teacher();
        promptHelper.println("");

        int input;
        do {
            input = promptHelper.readInt("Teacher ID: ");
            if (input <= 0){
                displayStatus(false, "ID should be > 0");
            }
        } while (input <= 0);

        int finalInput = input;
        return teachers.stream().filter(s -> s.getTeacherId() == finalInput).findFirst().orElse(null);
    }

    public Course chooseCourse(List<Course> courses) {
        promptHelper.println("");
        displayCourses(courses);
        if (courses == null || courses.isEmpty()) return new Course();
        promptHelper.println("");

        int input;
        do {
            input = promptHelper.readInt("Course ID: ");
            if (input <= 0){
                displayStatus(false, "ID should be > 0");
            }
        } while (input <= 0);

        int finalInput = input;
        return courses.stream().filter(s -> s.getCourseId() == finalInput).findFirst().orElse(null);
    }

    public void displayStatus(boolean status, String messages) {
        displayHeader(status ? "Success" : "Error");
            promptHelper.println(messages);
    }
    public String getName() {
        String name;
        //make sure name is valid, contains only alphabets no numeric values
        do {
            name = promptHelper.readRequiredString("Enter a valid name: ");
        } while (!name.matches("^[a-zA-Z\\s]*$"));
        System.out.println(name);
        return name;
    }

    public String getEmail() {
        String email;
        do {
            email = promptHelper.readRequiredString("Enter a valid email: ");
        } while (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        System.out.println(email);
        return email;
    }

    public int getAge() {
        // valid age, assuming application is for college use, set age restriction to 17 - 70? maybe?
        int age;
        do {
            age = promptHelper.readInt("Enter a valid age[17 - 70]: ");
        } while (age < 17 || age > 70);
        System.out.println(age);
        return age;
    }

    public String getSubject() {
        //valid subject name
        String subject;
        do {
            subject = promptHelper.readRequiredString("Enter a valid subject: ");
        } while (!subject.matches("^[a-zA-Z\\s]*$"));
        System.out.println(subject);
        return subject;
    }

    public int getGrade() {
        return promptHelper.readInt("Enter grade level[Freshman - 1, Sophomore - 2, ...]",1,4);
    }

    private String getGradeLevel(int grade){
        if (grade == 1) return "Freshman";
        else if (grade == 2) return "Sophomore";
        else if (grade == 3) return "Junior";
        else return "Senior";
    }

    public int getStudentId(){
        int input;
        do {
            input = promptHelper.readInt("Student ID: ");
            if (input <= 0){
                displayStatus(false, "ID should be > 0");
            }
        } while (input <= 0);

        return input;
    }

}
