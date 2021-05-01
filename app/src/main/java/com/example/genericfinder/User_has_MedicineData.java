package com.example.genericfinder;

import java.time.LocalDate;
import java.util.Date;

public class User_has_MedicineData {
    private String userEmail;
    private int medicineCode;
    private int price;
    private Date registerdDate;
    private int pharmacyBussinessNumber;

    public User_has_MedicineData(String userEmail, int medicineCode, int price, int pharmacyBussinessNumber) {
        this.userEmail = userEmail;
        this.medicineCode = medicineCode;
        this.price = price;
        this.registerdDate = new Date();
        this.pharmacyBussinessNumber = pharmacyBussinessNumber;
    }

    public User_has_MedicineData() {
        userEmail = "";
        medicineCode = 0;
        price = 0;
        registerdDate = new Date();
        pharmacyBussinessNumber = 0;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(int medicineCode) {
        this.medicineCode = medicineCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getRegisterdDate() {
        return registerdDate;
    }

    public void setRegisterdDate(Date registerdDate) {
        this.registerdDate = registerdDate;
    }

    public int getPharmacyBussinessNumber() {
        return pharmacyBussinessNumber;
    }

    public void setPharmacyBussinessNumber(int pharmacyBussinessNumber) {
        this.pharmacyBussinessNumber = pharmacyBussinessNumber;
    }
}
