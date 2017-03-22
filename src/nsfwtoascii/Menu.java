package nsfwtoascii;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class Menu {
    private Scanner scanner;
    private String[] categories;

    Menu() {
        scanner = new Scanner(System.in);
        categories = Scraper.getCommonCategoryList();
        Arrays.sort(categories);
    }

    void printMainMenu(boolean negative) {
        System.out.println("========== NSFW to ASCII Client ==========");
        System.out.println("1. Get an image");
        System.out.println("2. List of example NSFW categories");
        System.out.println("3. Invert image grayscale [Current mode: " + (negative ? "On" : "Off") + "]");
        System.out.println("4. Quit program");
    }

    void printListOfCategories() {
        System.out.println();
        System.out.println("The most common available categories are:");
        for (String category : categories) {
            System.out.println("- " + category);
        }
        System.out.println("\nThe above list is only a sample and many others are available.");
    }

    String getCategoryFromUser() {
        String str = "";

        while (str.isEmpty()) {
            System.out.print("\nPlease enter a category: ");
            str = scanner.next();
        }

        return str;
    }

    int getMenuChoice() {
        int choice = 0;

        while (choice < 1 || choice > 4) {
            System.out.print("\nPlease choose an option: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            else {
                scanner.next();
            }
        }

        return choice;
    }

    boolean returnToMenuPrompt() {
        System.out.println("\nPress \"return\" key to go back to main menu...");
        try {
            System.in.read();
            return true;
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return false;
    }
}
