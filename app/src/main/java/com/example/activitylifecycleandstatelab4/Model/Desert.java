package com.example.activitylifecycleandstatelab4.Model;

public class Desert {

    private int imageId, price, startProductionAmount;

    public Desert(int imageId, int price, int startProductionAmount) {
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
