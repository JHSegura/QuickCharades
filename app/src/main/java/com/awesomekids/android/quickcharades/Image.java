package com.awesomekids.android.quickcharades;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public class Image {
    private int imageID;
    private String imageName;
    private QCategory category;
    public Image(){
        imageName = "";
        category = QCategory.VOID;
        imageID = 0;
    }
    public Image(String name, QCategory category, int resource){
        imageName = name;
        this.category = category;
        imageID = resource;
    }
    public String getName(){return imageName;}
    public void setName(String name){imageName = name;}
    public QCategory getCategory(){return category;}
    public void setCategory(QCategory category){this.category = category;}
    public int getImageID(){return imageID;}
    public void setImageID(int resource){imageID = resource;}
}

