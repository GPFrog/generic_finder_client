package com.example.genericfinder;

public class InfoUserPriceData {
    private String pharmacyName;
    private String usersPrice;
    private String userEmail;

    public InfoUserPriceData(String pharmacyName, String usersPrice, String userEmail) {
        this.pharmacyName = pharmacyName;
        this.usersPrice = usersPrice;
        this.userEmail = userEmail;
    }

    public String getPharmacyName() { return pharmacyName; }
    public void setPharmacyName(String pharmacyName) { this.pharmacyName = pharmacyName; }

    public String getUsersPrice() { return usersPrice; }
    public void setUsersPrice(String usersPrice) { this.usersPrice = usersPrice; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
}
