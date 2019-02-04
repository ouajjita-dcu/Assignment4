package com.example.nadine.assign42019abdelkrimouajjit;
/**
 * {@link ProductsList} represents a single Android platform release.
 * Each object has 3 properties: name of the game,Description of the game, and image resource ID.
 */
public class ProductsList {
    private String gameName;
    private String gameDescription;
    private int mImageResourceId;
    /**
     *
     * @param vName :is the name of the game
     * @param vDescription:is the corresponding of the description of the game
     * @param imageResourceId :image is drawable reference ID that corresponds to the Android game.
     */
    public ProductsList(String vName, String vDescription, int imageResourceId)
    {
        gameName = vName;
        gameDescription = vDescription;
        mImageResourceId = imageResourceId;
    }
    // Getting the name of the game.
    public String getVersionName() {
        return gameName;
    }
    //Getting Description of the game.
    public String getVersionNumber() {
        return gameDescription;
    }
    // Getting the image resource ID
    public int getImageResourceId() {
        return mImageResourceId;
    }
}
