package com.example.genericfinder;

public class BookmarkData {
    private String bookmarkImg;
    private String bookmarkName;

    public BookmarkData(String bookmarkImg, String bookmarkName) {
        this.bookmarkImg =  bookmarkImg;
        this.bookmarkName = bookmarkName;
    }

    public BookmarkData() {
        bookmarkImg = "";
        bookmarkName = "";
    }

    public String getBookmarkImg() {return bookmarkImg;}
    public void setBookmarkImg(String bookmarkImg) {this.bookmarkImg = bookmarkImg;}

    public String getBookmarkName() {return bookmarkName; }
    public void setBookmarkName(String bookmarkName) {this.bookmarkName = bookmarkName; }
}
