package com.example.nadine.assign42019abdelkrimouajjit;
/**
 * {@link CollectionsList} represents a single Android platform release.
 * Each object has 3 properties: name of the store,Address of the store, and phone number of the store.
 */
public class CollectionsList {
    private String storeName;
    private String storeAddress;
    private String storePhone;
    /**
     *
     * @param storeName:is the name of the store.
     * @param storeAddress:is the corresponding of the store address.
     * @param storePhone:is the corresponding of the hone number of the store.
     */
    public CollectionsList(String storeName, String storeAddress, String storePhone)
    {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
       this.storePhone = storePhone;
    }
    // Getting the name of the store.
    public String getStoreName() {
        return storeName;
    }
    // Getting the address of the store.
    public String getStoreAddress() {
        return storeAddress;
    }
    // Getting the phone number of the store.
    public String getStorePhone() {
        return storePhone;
    }
}
