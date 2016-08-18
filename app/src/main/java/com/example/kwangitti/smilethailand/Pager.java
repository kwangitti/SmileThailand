package com.example.kwangitti.smilethailand;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kwangitti.smilethailand.ui.playlist.ProgramListFragment;

/**
 * Created by kwangitti on 8/15/16 AD.
 */
public class Pager extends FragmentStatePagerAdapter {

    int tabCount;

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomePlayFragment tab1 = new HomePlayFragment();
                return tab1;
            case 1:
                NewsFragment tab2 = new NewsFragment();
                return tab2;
            case 2:
                return ProgramListFragment.newInstance();
            case 3:
                AboutUsFragment tab4 = new AboutUsFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }


}
