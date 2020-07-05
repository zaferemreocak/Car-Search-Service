/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocak.zaferemre.carsearch;

import org.springframework.stereotype.Component;

/**
 *
 * @author Emre
 */
@Component
public class Car {
    
    private String brand;
    private String model;
    private String type;
    
    protected String getBrand() {
        return this.brand;
    }
    
    protected void setBrand(String brand) {
        this.brand = brand;
    }
    
    protected String getModel() {
        return this.model;
    }
    
    protected void setModel(String model) {
        this.model = model;
    }
    
    protected String getType() {
        return this.type;
    }
    
    protected void setType(String type) {
        this.type = type;
    }
    
}
