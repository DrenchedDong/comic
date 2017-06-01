package dongting.bwei.com.comic.tab_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import dongting.bwei.com.comic.R;
import dongting.bwei.com.comic.adapter.tab_adapter.HomeFragmentAdapter;
import dongting.bwei.com.comic.event.ScrollEvent;

/**
 * ${董婷}
 * 2017/4/25.
 */

public class HomeFragment extends Fragment {

    private ViewPager vp;
    private RadioGroup title_rg;
    private RelativeLayout title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.homefragment,container,false);
        vp = (ViewPager) view.findViewById(R.id.home_vp);
        title_rg = (RadioGroup) view.findViewById(R.id.title_rg);
        title = (RelativeLayout) view.findViewById(R.id.public_title);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        EventBus.getDefault().register(this);

        HomeFragmentAdapter homeFragmentAdapter =new HomeFragmentAdapter(getActivity().getSupportFragmentManager());
            vp.setAdapter(homeFragmentAdapter);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                title_rg.check(title_rg.getChildAt(position).getId());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        title_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                for( int i=0;i<2;i++){
                    if(title_rg.getChildAt(i).getId()==checkedId){
                        vp.setCurrentItem(i);
                    }
                }
            }
        });
    }

    //在订阅端定义方法，并使用相关属性判断状态
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onScrollEvent(ScrollEvent scrollEvent){

        //System.out.println("scrollevent"+scrollEvent.isUp());
        if(scrollEvent.isUp()){
title.setVisibility(View.GONE);
        }else{
            title.setVisibility(View.VISIBLE);
        }
    }
}