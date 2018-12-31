package com.example.nadine.assign42019abdelkrimouajjit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    public static final int Home = 0;
    public static final int PRODUCTS = 1;
    public static final int ORDERS = 2;
    public static final int COLLECTION = 3;
    public static final String UI_TAB_HOME = "HOME";
    public static final String UI_TAB_PRODUCTS = "PRODUCTS";
    public static final String UI_TAB_ORDERS = "ORDERS";
    public static final String UI_TAB_COLLECTION = "COLLECTION";

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case Home:
                HomeFragment homeTab = new HomeFragment();
                return homeTab;
            case PRODUCTS:
                ProductsFragment productsTab = new ProductsFragment();
                return productsTab;
            case ORDERS:
                OrdersFragment ordersTab = new OrdersFragment();
                return ordersTab;
            case COLLECTION:
                CollectionFragment collectionTab = new CollectionFragment();
                return collectionTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return UI_TAB_HOME;
            case 1:
                return UI_TAB_PRODUCTS;
            case 2:
                return UI_TAB_ORDERS;
            case 3:
                return UI_TAB_COLLECTION;

            default:
                break;
        }
        return null;
    }
}
