package com.project.dealer_system.inventory.entity;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"dealerId", "vin"})
})
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vin;
    private Long dealerId;

    private Double price;
    private Integer mileage;

    private boolean sold = false;

    // ✅ GETTERS
    public Long getId() {
        return id;
    }

    public String getVin() {
        return vin;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getMileage() {
        return mileage;
    }

    public boolean isSold() {
        return sold;
    }

    // ✅ SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}