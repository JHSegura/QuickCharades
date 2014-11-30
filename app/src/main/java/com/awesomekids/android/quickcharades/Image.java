package com.awesomekids.android.quickcharades;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public class Image {
    //Image URL?
    private String imageName;
    private QCategory category;
    public Image(){
        imageName = "";
        category = QCategory.VOID;
    }
    public Image(String name, QCategory category){
        imageName = name;
        this.category = category;
    }
    public String getName(){
        return imageName;
    }
    public void setName(String name){
        imageName = name;
    }
    public QCategory getCategory(){
        return category;
    }
    public void setCategory(QCategory category){
        this.category = category;
    }
    //Show Image Method
}

