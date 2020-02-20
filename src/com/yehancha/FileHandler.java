package com.yehancha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    public static InputFile readInputFile(String fileName) {
        InputFile inputFile = null;

        try {
            inputFile = new InputFile();

            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            // Reading the first line
            String[] data = scanner.nextLine().split(" ");
            int bookCount = Integer.parseInt(data[0]);
            int libraryCount = Integer.parseInt(data[1]);
            inputFile.setScanningDayCount(Integer.parseInt(data[2]));

            // Reading the second line
            data = scanner.nextLine().split(" ");
            ArrayList<InputFile.Book> books = inputFile.getBooks();
            for (int i = 0; i < bookCount; i++) {
                InputFile.Book book = new InputFile.Book();
                book.setId(i);
                book.setScore(Integer.parseInt(data[i]));

                books.add(book);
            }

            // Reading libraries
            ArrayList<InputFile.Library> libraries = inputFile.getLibraries();
            for (int i = 0; i < libraryCount; i++) {
                InputFile.Library library = new InputFile.Library();
                library.setId(i);

                // Reading the first line of library
                data = scanner.nextLine().split(" ");
                int libraryBookCount = Integer.parseInt(data[0]);
                library.setSignUpDays(Integer.parseInt(data[1]));
                library.setBooksPerDay(Integer.parseInt(data[2]));

                // Reading the second line of library
                data = scanner.nextLine().split(" ");
                ArrayList<InputFile.Book> libraryBooks = library.getBooks();
                for (int j = 0; j < libraryBookCount; j++) {
                    int bookId = Integer.parseInt(data[j]);
                    libraryBooks.add(books.get(bookId));
                }

                libraries.add(library);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return inputFile;
    }

    public static void writeOutputFile(OutputFile file, String fileName) {
        try {
            ArrayList<OutputFile.SendingLibraryInfo> libraries = file.getLibraries();

            String outputString = "";
            outputString += libraries.size() + "\n";

            for (OutputFile.SendingLibraryInfo libraryInfo : libraries) {
                ArrayList<InputFile.Book> books = libraryInfo.getBooks();

                outputString += libraryInfo.getLibrary().getId() + " " + books.size() + "\n";

                String bookIdString = "";
                for (InputFile.Book book : books) {
                    bookIdString += book.getId() + " ";
                }

                outputString += bookIdString.substring(0, bookIdString.length() - 1) + "\n";
            }

            outputString = outputString.substring(0, outputString.length() - 1);

            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(outputString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
