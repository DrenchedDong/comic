package dongting.bwei.com.comic.adapter.tab_adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import dongting.bwei.com.comic.tab_fragment.home_fragment.FocusFragment;
import dongting.bwei.com.comic.tab_fragment.home_fragment.HotFragment;

/**
 * ${董婷}
 * 2017/4/25.
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter{

    public HomeFragmentAdapter(FragmentManager fragmentManager){
                super(fragmentManager);

    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new FocusFragment();

                break;
            case 1:
                fragment=new HotFragment();

                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
