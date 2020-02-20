package com.yehancha;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    private static String filePath = "/home/yehancha/IdeaProjects/GoogleHashCode2020/src/com/yehancha/";

    public static void main(String[] args) {
        process("a_example");
    }

    private static void process(String dataset) {
        InputFile inputFile = FileHandler.readInputFile(filePath + dataset + ".txt");
        OutputFile outputFile = new OutputFile();

        outputFile.getLibraries().addAll(getLibraryInfos(inputFile));

        FileHandler.writeOutputFile(outputFile, filePath + dataset + "_out.txt");
    }

    private static ArrayList<OutputFile.SendingLibraryInfo> getLibraryInfos(InputFile inputFile) {
        ArrayList<OutputFile.SendingLibraryInfo> libraryInfos = new ArrayList<>();

        ArrayList<InputFile.Library> libraries = inputFile.getLibraries();

        while (libraries.size() > 0) {
            scoreAndSortLibraries(libraries);
            InputFile.Library library = removeTopLibrary(libraries);

            OutputFile.SendingLibraryInfo info = new OutputFile.SendingLibraryInfo();
            info.setLibrary(library);
            info.setBooks(library.getBooks());

            libraryInfos.add(info);
        }

        return libraryInfos;
    }

    private static void scoreAndSortLibraries(ArrayList<InputFile.Library> libraries) {
        for (InputFile.Library library : libraries) {
            ArrayList<InputFile.Book> libraryBooks = library.getBooks();

            int totalBookScore = 0;
            for (InputFile.Book book : libraryBooks) {
                totalBookScore += book.getScore();
            }

            double totalDays = library.getSignUpDays() + (double) libraryBooks.size() / library.getBooksPerDay();

            library.setScore(totalBookScore / totalDays);
        }

        libraries.sort(new Comparator<InputFile.Library>() {
            @Override
            public int compare(InputFile.Library library1, InputFile.Library library2) {
                return library1.getScore() > library2.getScore() ? -1 : 1;
            }
        });

        for (InputFile.Library library : libraries) {
            System.out.println(library.getId() + ": " + library.getScore());
        }
    }

    private static InputFile.Library removeTopLibrary(ArrayList<InputFile.Library> libraries) {
        InputFile.Library top = libraries.remove(0);
        ArrayList<InputFile.Book> topLibraryBooks =  top.getBooks();

        // Remove duplicate books
        for (InputFile.Library library : libraries) {
            ArrayList<InputFile.Book> books =  library.getBooks();
            for (InputFile.Book topLibraryBook : topLibraryBooks) {
                books.remove(topLibraryBook);
            }
        }

        return top;
    }
}
