package dongting.bwei.com.comic.tab_fragment.home_fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import dongting.bwei.com.comic.R;
import dongting.bwei.com.comic.adapter.title_adapter.Home_title_adapter;
import dongting.bwei.com.comic.event.ScrollEvent;
import dongting.bwei.com.comic.listener.ResponseListener;

/**
 * ${董婷}
 * 2017/4/25.
 */

public class HotFragment extends Fragment{

    private ViewPager vp;
    private List<View> list = new ArrayList<View>();;
    private TabLayout tabLayout;
    private ListView home_hot_lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.home_hot_fragment,container,false);

        vp = (ViewPager) view.findViewById(R.id.hot_vp);
        tabLayout = (TabLayout) view.findViewById(R.id.week_title);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Home_title_adapter homeTitleAdapter =new Home_title_adapter(getActivity().getSupportFragmentManager());

        vp.setAdapter(homeTitleAdapter);

        //使viewpager与tabLayout进行关联
        tabLayout.setupWithViewPager(vp);
        //tabLayout选中的字体颜色
        tabLayout.setTabTextColors(getResources().getColor(R.color.tab_text_no),getResources().getColor(R.color.title_text));
        //tabLayout下划线的颜色
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.underline));
        //设置滑动的流动性
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

}
