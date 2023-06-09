package com.example.activitylifecycleandstatelab4.Model;


/**
 * Model class with accessors and mutators for Dessert class.
 */
public class Dessert {

    private int imageId, price, startProductionAmount;

    public Dessert(int imageId, int price, int startProductionAmount) {
        this.imageId = imageId;
        this.price = price;
        this.startProductionAmount = startProductionAmount;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStartProductionAmount() {
        return startProductionAmount;
    }

    public void setStartProductionAmount(int startProductionAmount) {
        this.startProductionAmount = startProductionAmount;
    }
}
