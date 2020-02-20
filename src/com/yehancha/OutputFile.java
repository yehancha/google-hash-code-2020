package com.yehancha;

import java.util.ArrayList;

public class OutputFile {
    private ArrayList<SendingLibraryInfo> libraries = new ArrayList<>();

    public ArrayList<SendingLibraryInfo> getLibraries() {
        return libraries;
    }

    public void setLibraries(ArrayList<SendingLibraryInfo> libraries) {
        this.libraries = libraries;
    }

    public static class SendingLibraryInfo {
        private InputFile.Library library;
        private ArrayList<InputFile.Book> books = new ArrayList<>();

        public InputFile.Library getLibrary() {
            return library;
        }

        public void setLibrary(InputFile.Library library) {
            this.library = library;
        }

        public ArrayList<InputFile.Book> getBooks() {
            return books;
        }

        public void setBooks(ArrayList<InputFile.Book> books) {
            this.books = books;
        }
    }
}
