package com.example.genericfinder;

public class CompanyCheckData {
    private String company1;
    private String company2;

    public CompanyCheckData(String company1, String company2) {
        this.company1 = company1;
        this.company2 = company2;
    }

    public String getCompany1() { return company1; }
    public void setCompany1(String company1) { this.company1 = company1; }

    public String getCompany2() { return company2; }
    public void setCompany2(String company2) { this.company2 = company2; }
}
