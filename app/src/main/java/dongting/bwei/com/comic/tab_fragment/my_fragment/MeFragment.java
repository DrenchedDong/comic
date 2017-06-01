package dongting.bwei.com.comic.tab_fragment.my_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dongting.bwei.com.comic.R;

public class MeFragment extends Fragment {

    private ListView listView;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.listview_me);
        initData();
        listView.setAdapter(new Myadapter());

        return view;
    }

    private void initData() {
        list = new ArrayList<>();
        list.add("我的消息");
        list.add("我的关注");
        list.add("我的收藏");
        list.add("快看游戏");
        list.add("快看商城");
        list.add("我的订单");
        list.add("浏览历史");
        list.add("智能缓存");
        list.add("设置");

    }

    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder= null;

            if (convertView == null) {
                viewHolder=new ViewHolder();
                convertView = View.inflate(getActivity(), R.layout.item_me, null);
      viewHolder.textviewItemMe= (TextView) convertView.findViewById(R.id.textview_item_me);

           convertView.setTag(viewHolder);

            } else {
  viewHolder = (ViewHolder) convertView.getTag();

             }
     viewHolder.textviewItemMe.setText(list.get(position));


            return convertView;
        }

         class ViewHolder {
            TextView textviewItemMe;

        }
    }
}
