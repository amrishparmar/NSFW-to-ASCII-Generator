package nsfwtoascii;

import java.awt.image.BufferedImage;
import java.io.IOException;


public class NsfwToAscii {
    private static ASCII ascii;

    public static void main(String[] args) throws IOException {
        ascii = new ASCII(true);
        start();
    }

    private static void start() {
        Menu menu = new Menu();

        menu.printMainMenu(ascii.isNegative());

        int choice = menu.getMenuChoice();

        switch (choice) {
            case 1:
                getAnImage(menu.getCategoryFromUser());
                start();
                break;
            case 2:
                menu.printListOfCategories();
                if (menu.returnToMenuPrompt()) {
                    start();
                }
                break;
            case 3:
                ascii.reverseNegative();
                System.out.println("\nImage preference updated.");
                start();
                break;
            case 4:
                System.exit(0);
        }
    }

    private static void getAnImage(String category) {
        try {
            BufferedImage img = ImageHandler.loadImageFromUrl(Scraper.getImageUrlFromCategory(category));
            System.out.println(ascii.convert(img));
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        System.out.println("\r\nPress \"return\" key to go back to main menu...");
        try {
            System.in.read();
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

}