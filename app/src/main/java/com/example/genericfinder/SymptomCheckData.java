package com.example.genericfinder;

public class SymptomCheckData {
    private String symptom1;
    private String symptom2;

    public SymptomCheckData(String symptom1, String symptom2) {
        this.symptom1 = symptom1;
        this.symptom2 = symptom2;
    }

    public String getSymptom1() { return symptom1; }
    public void setSymptom1(String symptom1) {  this.symptom1 = symptom1; }

    public String getSymptom2() { return symptom2; }
    public void setSymptom2(String symptom2) {  this.symptom2 = symptom2; }
}
