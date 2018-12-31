package com.example.nadine.assign42019abdelkrimouajjit;

public class ProductsList {
    private String gameName;
    private String gameDescription;
    private int mImageResourceId;

    public ProductsList(String vName, String vDescription, int imageResourceId)
    {
        gameName = vName;
        gameDescription = vDescription;
        mImageResourceId = imageResourceId;
    }

    public String getVersionName() {
        return gameName;
    }

    public String getVersionNumber() {
        return gameDescription;
    }
 
    public int getImageResourceId() {
        return mImageResourceId;
    }
}
