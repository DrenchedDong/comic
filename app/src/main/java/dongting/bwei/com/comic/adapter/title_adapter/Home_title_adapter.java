package dongting.bwei.com.comic.adapter.title_adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dongting.bwei.com.comic.tab_fragment.home_fragment.WeekFragment;

/**
 * ${董婷}
 * 2017/4/26.
 */

public class Home_title_adapter extends FragmentPagerAdapter {
    String[] week = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};

    public Home_title_adapter(FragmentManager fragmentManager) {
super(fragmentManager);
    }

    @Override
    public int getCount() {
        return week.length;
    }

    @Override
    public Fragment getItem(int position) {

        WeekFragment weekFragment =new WeekFragment();

        Bundle bundle =new Bundle();
        bundle.putString("title",week[position]);

        weekFragment.setArguments(bundle);

        return weekFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return week[position];
    }
}