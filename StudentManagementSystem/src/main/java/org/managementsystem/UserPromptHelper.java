package org.managementsystem;

import java.util.Scanner;

public class UserPromptHelper {
    private final Scanner scanner = new Scanner(System.in);

    public void print(String message) {System.out.print(message);}
    public void println(String message) {System.out.println(message);}
    public void printf(String format, Object... values) {System.out.printf(format, values);}

    public String readString(String prompt) {
        print(prompt);
        return scanner.nextLine();
    }

    public boolean isBlank(String prompt){
        return prompt == null || prompt.trim().isEmpty() || prompt.matches("[\\n\\r\\s+]");
    }
    public String readRequiredString(String prompt) {
        while (true) {
            String result = readString(prompt);
            if (!isBlank(result)) {
                return result;
            }
            println("[INVALID] Value is required.");
        }
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readRequiredString(prompt));
            } catch (NumberFormatException exception) {
                println("[INVALID] Enter a valid number.");
            }
        }
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            int result = readInt(prompt);
            if (result >= min && result<= max) return result;
            println(String.format("[INVALID] Enter a number between %s and %s.",min,max));
        }
    }

}
