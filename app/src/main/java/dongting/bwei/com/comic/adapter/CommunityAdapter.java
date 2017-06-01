package dongting.bwei.com.comic.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import dongting.bwei.com.comic.tab_fragment.home_fragment.WeekFragment;

/**
 * Created by lenovo on 2017/5/1.
 */

public class CommunityAdapter extends FragmentPagerAdapter {

     String []TITLE={"热门","最新"};

    public CommunityAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        WeekFragment weekFragment= new WeekFragment();
        return weekFragment;
    }

    @Override
    public int getCount() {
        return TITLE.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position];
    }


}
