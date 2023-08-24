package com.hoangtien2k3.food_order.Domain;

public class FoodDomain {
    private String title;
    private String pic;
    private String descroption;
    private Double fee;
    private int numberInCart;
    private int star;
    private int time;
    private int calories;

    public FoodDomain(String title, String pic, String descroption, Double fee, int numberInCart, int star, int time) {
        this.title = title;
        this.pic = pic;
        this.descroption = descroption;
        this.fee = fee;
        this.numberInCart = numberInCart;
        this.star = star;
        this.time = time;
        this.calories = calories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescroption() {
        return descroption;
    }

    public void setDescroption(String descroption) {
        this.descroption = descroption;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
