package org.managementsystem;

public class StartApplication {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        //inject dependencies and run app
        UserPromptHelper promptHelper = new UserPromptHelper();
        AppHelper appHelper1 = new AppHelper(promptHelper);
        App app = new App(appHelper1);
        app.run();
    }
}