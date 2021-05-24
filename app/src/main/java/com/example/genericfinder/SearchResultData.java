package com.example.genericfinder;

public class SearchResultData {
    private String resultName;
    private String resultPrice;
    private String resultImg;


    public SearchResultData(String resultName, String resultPrice, String resultImg) {
        this.resultName = resultName;
        this.resultPrice = resultPrice;
        this.resultImg = resultImg;
    }

    public SearchResultData() {
        resultName = "";
        resultPrice = "";
        resultImg = "";
    }

    public SearchResultData(String medicine) {
        String[] items = medicine.split("/");
        this.resultName = items[0].trim();
        this.resultPrice = items[1].trim();
        this.resultImg = items[2].trim();
    }

    public String getResultName() {return resultName;}
    public void setResultName(String resultName) {this.resultName = resultName;}

    public String getResultPrice() {return resultPrice;}
    public void setResultPrice(String resultPrice) {this.resultPrice = resultPrice;}

    public String getResultImg() {return resultImg;}
    public void setResultImg(String resultImg) {this.resultImg = resultImg;}
}
