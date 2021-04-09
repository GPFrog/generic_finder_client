package com.example.genericfinder;

public class BookmarkData {
    private int bookmarkImg;
    private String bookmarkName;

    public void BookmarkData(int bookmarkImg, String bookmarkName) {
        this.bookmarkImg =  bookmarkImg;
        this.bookmarkName = bookmarkName;
    }

    public void BookmarkData() {
        bookmarkImg = 0;
        bookmarkName = "";
    }

    public int getBookmarkImg() {return bookmarkImg;}
    public void setBookmarkImg(int bookmarkImg) {this.bookmarkImg = bookmarkImg;}

    public String getBookmarkName() {return bookmarkName; }
    public void setBookmarkName(String bookmarkName) {this.bookmarkName = bookmarkName; }
}
