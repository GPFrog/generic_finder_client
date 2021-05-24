package com.example.genericfinder;

public class MedicinePriceData {
    private String enrollDate;
    private String pName;
    private String mName;
    private String mPrice;
    private String userEmail;

    public MedicinePriceData(String enrollDate, String pName, String mName, String mPrice, String userEmail) {
        this.enrollDate = enrollDate;
        this.pName = pName;
        this.mName = mName;
        this.mPrice = mPrice;
        this.userEmail = userEmail;
    }

    public MedicinePriceData() {
        enrollDate = "";
        pName = "";
        mName = "";
        mPrice = "";
        userEmail = "";
    }

    public String getEnrollDate() {return enrollDate;}
    public void setEnrollDate(String enrollDate) {this.enrollDate = enrollDate;}

    public String getpName() {return pName;}
    public void setpName(String pName) {this.pName = pName;}

    public String getmName() {return mName;}
    public void setmName(String mName) {this.mName = mName;}

    public String getmPrice() {return mPrice;}
    public void setmPrice(String mPrice) {this.mPrice = mPrice;}

    public String getUserEmail() {return userEmail;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
}
