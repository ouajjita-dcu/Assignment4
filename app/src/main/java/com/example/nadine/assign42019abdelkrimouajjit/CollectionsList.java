package com.example.nadine.assign42019abdelkrimouajjit;

public class CollectionsList {
    private String storeName;
    private String storeAddress;
    private String storePhone;


    public CollectionsList(String storeName, String storeAddress, String storePhone)
    {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
       this.storePhone = storePhone;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public String getStorePhone() {
        return storePhone;
    }
}
