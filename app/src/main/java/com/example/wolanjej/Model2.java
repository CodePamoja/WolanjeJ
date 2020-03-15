package com.example.wolanjej.cryptobalance;

public class Model {
    private int image;

    private String  title;
    private int color;

    /**
     * @return
     */
    public int getColor(){return color;}

    public void setColor(int color){this.color = color;}

    public int getImage() {
        return image;
    }

    public  void  setImage(int image){
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public  void setTitle(String title){
        this.title = title;
    }

}
