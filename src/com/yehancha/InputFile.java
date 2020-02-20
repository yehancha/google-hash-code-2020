package com.yehancha;

import java.util.ArrayList;

public class InputFile {
    private int scanningDayCount;
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Library> libraries = new ArrayList<>();

    public int getScanningDayCount() {
        return scanningDayCount;
    }

    public void setScanningDayCount(int scanningDayCount) {
        this.scanningDayCount = scanningDayCount;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(ArrayList<Library> libraries) {
        this.libraries = libraries;
    }

    public static class Book {
        private int id;
        private int score;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

    public static class Library {
        private int id;
        private int signUpDays;
        private int booksPerDay;
        private ArrayList<Book> books = new ArrayList<>();

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSignUpDays() {
            return signUpDays;
        }

        public void setSignUpDays(int signUpDays) {
            this.signUpDays = signUpDays;
        }

        public int getBooksPerDay() {
            return booksPerDay;
        }

        public void setBooksPerDay(int booksPerDay) {
            this.booksPerDay = booksPerDay;
        }

        public ArrayList<Book> getBooks() {
            return books;
        }

        public void setBooks(ArrayList<Book> books) {
            this.books = books;
        }
    }
}
