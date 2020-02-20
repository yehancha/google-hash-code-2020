package com.yehancha;

public class Main {
    private static String filePath = "/home/yehancha/IdeaProjects/GoogleHashCode2020/src/com/yehancha/";

    public static void main(String[] args) {
        process("a_example");
    }

    private static void process(String dataset) {
        InputFile inputFile = FileHandler.readInputFile(filePath + dataset + ".txt");
        OutputFile outputFile = new OutputFile();

        OutputFile.SendingLibraryInfo libraryInfo = new OutputFile.SendingLibraryInfo();
        libraryInfo.setLibrary(inputFile.getLibraries().get(1));
        libraryInfo.getBooks().add(inputFile.getBooks().get(5));
        libraryInfo.getBooks().add(inputFile.getBooks().get(2));
        libraryInfo.getBooks().add(inputFile.getBooks().get(3));

        outputFile.getLibraries().add(libraryInfo);

        libraryInfo = new OutputFile.SendingLibraryInfo();
        libraryInfo.setLibrary(inputFile.getLibraries().get(0));
        libraryInfo.getBooks().add(inputFile.getBooks().get(0));
        libraryInfo.getBooks().add(inputFile.getBooks().get(1));
        libraryInfo.getBooks().add(inputFile.getBooks().get(2));
        libraryInfo.getBooks().add(inputFile.getBooks().get(3));
        libraryInfo.getBooks().add(inputFile.getBooks().get(4));

        outputFile.getLibraries().add(libraryInfo);

        FileHandler.writeOutputFile(outputFile, filePath + dataset + "_out.txt");
    }
}
