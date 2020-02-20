package com.yehancha;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    private static String filePath = "/home/yehancha/IdeaProjects/GoogleHashCode2020/src/com/yehancha/";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        process("a_example");
        process("b_read_on");
        process("c_incunabula");
        process("d_tough_choices");
        process("e_so_many_books");
        process("f_libraries_of_the_world");
        long end = System.currentTimeMillis();
        System.out.println("Processing time: " + (end - start) / 1000 + "s");
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

        int totalDaysForSignUp = 0;
        while (libraries.size() > 0) {
            scoreAndSortLibraries(libraries);
            InputFile.Library library = removeTopLibrary(libraries);

            OutputFile.SendingLibraryInfo info = new OutputFile.SendingLibraryInfo();
            info.setLibrary(library);
            info.setBooks(library.getBooks());

            libraryInfos.add(info);

            totalDaysForSignUp += library.getSignUpDays();

            System.out.println("Choose library: " + library.getId() + " with " + library.getBooks().size() + " books.");

            if (totalDaysForSignUp > inputFile.getScanningDayCount()) {
                System.out.println("We are out of days.");
                break;
            }
        }

        System.out.println("We have done.");
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
                double score1 = library1.getScore();
                double score2 = library2.getScore();

                if (score1 > score2) return -1;
                else if (score2 > score1) return 1;
                else return 0;
            }
        });
    }

    private static InputFile.Library removeTopLibrary(ArrayList<InputFile.Library> libraries) {
        InputFile.Library top = libraries.remove(0);
        ArrayList<InputFile.Book> topLibraryBooks =  top.getBooks();
        ArrayList<InputFile.Library> zeroBookLibraries = new ArrayList<>();

        // Remove duplicate books
        for (InputFile.Library library : libraries) {
            ArrayList<InputFile.Book> books =  library.getBooks();
            for (InputFile.Book topLibraryBook : topLibraryBooks) {
                books.remove(topLibraryBook);
            }

            if (books.size() == 0) zeroBookLibraries.add(library);
        }

        for (InputFile.Library library : zeroBookLibraries) {
            libraries.remove(library);
        }

        return top;
    }
}
