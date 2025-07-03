package org.managementsystem;

public enum MenuOption {

    EXIT(0,"Exit"),
    ADD_STUDENT(1,"Add a Student"),
    ADD_TEACHER(2,"Add a Teacher"),
    ADD_COURSE(3,"Add a Course"),
    ASSIGN_STUDENT_COURSE(4,"Assign Courses to Students"),
    ASSIGN_TEACHER_COURSE(5,"Assign Courses to Teachers"),
    VIEW_STUDENT(6,"View Student by ID"),
    VIEW_ALL(7,"View every Student, Teacher and Courses");

    private final int value;
    private final String message;

    private MenuOption(int value, String message){
        this.value      = value;
        this.message    = message;
    }

    public static MenuOption getMenu(int value) {
        for (MenuOption option : MenuOption.values()){
            if (option.getValue() == value){
                return option;
            }
        }
        return EXIT;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
