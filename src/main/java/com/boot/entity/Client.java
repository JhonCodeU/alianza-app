package com.boot.entity;

import java.time.LocalDate;

public class Client {
    private String sharedKey;
    private String businessId;
    private String email;
    private String phone;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private LocalDate dateCreate;

    // Constructores, getters y setters
    public Client(String sharedKey, String businessId, String email, String phone, LocalDate dateStart, LocalDate dateEnd, LocalDate dateCreate) {
        this.sharedKey = sharedKey;
        this.businessId = businessId;
        this.email = email;
        this.phone = phone;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dateCreate = dateCreate;
    }

    // Getters y setters...

    public String getSharedKey() {
        return sharedKey;
    }

    public void setSharedKey(String sharedKey) {
        this.sharedKey = sharedKey;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }
}