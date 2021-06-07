package com.example.genericfinder;

public class SearchResultData {
    private String resultName;
    private String resultPrice;

    public SearchResultData() {
        resultName = "";
        resultPrice = "";
    }

    public SearchResultData(String resultName, String resultPrice) {
        this.resultName = resultName;
        this.resultPrice = resultPrice;
    }

    public SearchResultData(String medicine) {
        String[] items = medicine.split("/");
        this.resultName = items[0].trim();
        this.resultPrice = items[1].trim();
    }

    public String getResultName() {return resultName;}
    public void setResultName(String resultName) {this.resultName = resultName;}

    public String getResultPrice() {return resultPrice;}
    public void setResultPrice(String resultPrice) {this.resultPrice = resultPrice;}
}
