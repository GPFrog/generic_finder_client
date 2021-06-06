package com.example.genericfinder;

public class BookmarkData {
    private String bookmarkName;

    public BookmarkData(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public BookmarkData() {
        bookmarkName = "";
    }

    public String getBookmarkName() {return bookmarkName; }
    public void setBookmarkName(String bookmarkName) {this.bookmarkName = bookmarkName; }
}
