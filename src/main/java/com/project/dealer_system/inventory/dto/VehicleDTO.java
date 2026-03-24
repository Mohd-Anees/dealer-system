package com.project.dealer_system.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VehicleDTO {

    @NotBlank(message = "VIN must not be blank")
    private String vin;

    @NotNull(message = "Dealer ID must not be null")
    private Long dealerId;

    private Double price;
    private Integer mileage;

    public String getVin() { return vin; }
    public Long getDealerId() { return dealerId; }
    public Double getPrice() { return price; }
    public Integer getMileage() { return mileage; }

    public void setVin(String vin) { this.vin = vin; }
    public void setDealerId(Long dealerId) { this.dealerId = dealerId; }
    public void setPrice(Double price) { this.price = price; }
    public void setMileage(Integer mileage) { this.mileage = mileage; }
}