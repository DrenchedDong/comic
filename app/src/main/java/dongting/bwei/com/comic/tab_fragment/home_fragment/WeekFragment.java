package dongting.bwei.com.comic.tab_fragment.home_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import dongting.bwei.com.comic.MyAsyncTask;
import dongting.bwei.com.comic.R;
import dongting.bwei.com.comic.adapter.week_adapter.WeekAdapter;
import dongting.bwei.com.comic.bean.MondayBean;
import dongting.bwei.com.comic.event.ScrollEvent;
import dongting.bwei.com.comic.listener.ResponseListener;

/**
 * ${董婷}
 * 2017/4/28.
 */

public class WeekFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,ResponseListener{

    int index=0;

    private ViewPager viewPager;
    private TextView tv_title;
    private SwipeRefreshLayout swipe;
    private ListView lv;

    private List<MondayBean.DataBean.ComicsBean> list=new ArrayList<MondayBean.DataBean.ComicsBean>();
    private WeekAdapter weekAdapter;

    @Nullable
    @Override
    public View onCreateView
            (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
      View v=inflater.inflate(R.layout.home_hot_listview,container,false);

        swipe = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefresh);

        swipe.setOnRefreshListener(this);

        lv = (ListView) v.findViewById(R.id.home_hot_lv);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

        weekAdapter = new WeekAdapter(getActivity(),list);
        lv.setAdapter(weekAdapter);

       // EventBus.getDefault().post(new ScrollEvent(true));

        //listview的滑动监听
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if(scrollState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    index = lv.getLastVisiblePosition();

                }

                if(scrollState== AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    if(lv.getLastVisiblePosition()-index>0){
                        //上滑
                        EventBus.getDefault().post(new ScrollEvent(true));
                    }else{
                        //下滑
                        EventBus.getDefault().post(new ScrollEvent(false));
                    }

                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    //定义网络请求数据方法
    private void initData() {

        String path="http://api.kkmh.com/v1/daily/comic_lists/0?since=0&gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3ODI4ODU4NjAwLCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjIuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjM0IiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6InNhbXN1bmciLCJGcm9tSG9tZXBhZ2VVcGRhdGVEYXRlIjowLCIkc2NyZWVuX2hlaWdodCI6NTc2LCJIb21lcGFnZVVwZGF0ZURhdGUiOjAsIlByb3BlcnR5RXZlbnQiOiJSZWFkSG9tZVBhZ2UiLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6NDYsIiRzY3JlZW5fd2lkdGgiOjEwMjQsIiRvcyI6IkFuZHJvaWQiLCJUcmlnZ2VyUGFnZSI6IkhvbWVQYWdlIiwiJGNhcnJpZXIiOiJDTUNDIiwiJG1vZGVsIjoiR1QtUDUyMTAiLCIkYXBwX3ZlcnNpb24iOiIzLjguMSJ9LCJ0eXBlIjoidHJhY2siLCJkaXN0aW5jdF9pZCI6IkE6OTA1MTA0Mjc2Mzc1NTEwOSIsIm9yaWdpbmFsX2lkIjoiQTo5MDUxMDQyNzYzNzU1MTA5IiwiZXZlbnQiOiJSZWFkSG9tZVBhZ2UifQ%3D%3D";

        new MyAsyncTask(this).execute(path);
    }

    @Override
    public void onRefresh() {
initData();
    }

    @Override
    public void onSuccess(Object str) {
        //System.out.println(str);

        swipe.setRefreshing(false);

        Gson gson =new Gson();
        MondayBean mondayBean = gson.fromJson((String)str, MondayBean.class);

        List<MondayBean.DataBean.ComicsBean> comicsBeanList = mondayBean.getData().getComics();
        list.addAll(comicsBeanList);

        weekAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFail() {

    }
}
