package com.mediscreen.patient.model;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    private long id;
    private int number;
    private String street;
    private String city;
    private int zipCode;
    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Address() {
    }

    public Address(long id, int number, String street, String city, int zipCode, Patient patient) {
        this.id = id;
        this.number = number;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.patient = patient;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
