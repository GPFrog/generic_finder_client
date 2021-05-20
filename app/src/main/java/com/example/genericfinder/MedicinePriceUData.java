package com.example.genericfinder;

public class MedicinePriceUData {
    private String enrollDate;
    private String pName;
    private String mName;
    private String mPrice;

    public MedicinePriceUData(String enrollDate, String pName, String mName, String mPrice, String userEmail) {
        this.enrollDate = enrollDate;
        this.pName = pName;
        this.mName = mName;
        this.mPrice = mPrice;
    }

    public MedicinePriceUData() {
        enrollDate = "";
        pName = "";
        mName = "";
        mPrice = "";
    }

    public String getEnrollDate() {return enrollDate;}
    public void setEnrollDate(String enrollDate) {this.enrollDate = enrollDate;}

    public String getpName() {return pName;}
    public void setpName(String pName) {this.pName = pName;}

    public String getmName() {return mName;}
    public void setmName(String mName) {this.mName = mName;}

    public String getmPrice() {return mPrice;}
    public void setmPrice(String mPrice) {this.mPrice = mPrice;}
}
