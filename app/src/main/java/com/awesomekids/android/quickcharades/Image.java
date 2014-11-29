package com.awesomekids.android.quickcharades;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public class Image {
    //Image URL?
    private String imageName;
    private Category category;
    public Image(){
        imageName = "";
        category = Category.VOID;
    }
    public Image(String name, Category category){
        imageName = name;
        this.category = category;
    }
    public String getName(){
        return imageName;
    }
    public void setName(String name){
        imageName = name;
    }
    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category){
        this.category = category;
    }
    //Show Image Method
}

