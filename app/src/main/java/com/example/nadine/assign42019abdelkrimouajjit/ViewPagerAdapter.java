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

    /**
     *
     * @param position:ViewPagerAdapter
     * @return : Returning the collectionTab.
     */
    @Override
    // Returns total number of fragments.
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

    /**
     *
     * @return: Retuning the one of the tabs.
     */
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    // Returns the page title for the top indicator
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                // This will show home fragment.
                return UI_TAB_HOME;
            case 1:
                // This will show product fragment.
                return UI_TAB_PRODUCTS;
            case 2:
                // This will show order fragment.
                return UI_TAB_ORDERS;
            case 3:
                // This will show collection fragment.
                return UI_TAB_COLLECTION;

            default:
                break;
        }
        return null;
    }
}
