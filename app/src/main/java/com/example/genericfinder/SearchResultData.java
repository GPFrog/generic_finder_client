package com.example.genericfinder;

public class SearchResultData {
    private String resultName;
    private String resultPrice;
    private int resultImg;

    public SearchResultData(String resultName, String resultPrice, int resultImg) {
        this.resultName = resultName;
        this.resultPrice = resultPrice;
        this.resultImg = resultImg;
    }

    public SearchResultData() {
        resultName = "";
        resultPrice = "";
        resultImg = 0;
    }

    public String getResultName() {return resultName;}
    public void setResultName(String resultName) {this.resultName = resultName;}

    public String getResultPrice() {return resultPrice;}
    public void setResultPrice(String resultPrice) {this.resultPrice = resultPrice;}

    public int getResultImg() {return resultImg;}
    public void setResultImg(int resultImg) {this.resultImg = resultImg;}
}
