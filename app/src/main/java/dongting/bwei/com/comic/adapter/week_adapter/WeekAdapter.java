package dongting.bwei.com.comic.adapter.week_adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import dongting.bwei.com.comic.R;
import dongting.bwei.com.comic.bean.MondayBean;

/**
 * ${董婷}
 * 2017/4/28.
 */

public class WeekAdapter extends BaseAdapter {
    List<MondayBean.DataBean.ComicsBean> list;
    Context context;

    public WeekAdapter( Context context,List<MondayBean.DataBean.ComicsBean> list) {
        this.list = list;
        this.context = context;
    }

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
        if(convertView==null){
            convertView=convertView.inflate(context,R.layout.home_hot_lv_item,null);
        }
       TextView label=(TextView) convertView.findViewById(R.id.label_text);

        TextView title=(TextView) convertView.findViewById(R.id.week_item_title);

        ImageView imageView=(ImageView) convertView.findViewById(R.id.week_item_imageview);

        TextView below_title=(TextView) convertView.findViewById(R.id.week_item_below_title);

        ImageView iv_zan=(ImageView) convertView.findViewById(R.id.week_item_image_zan);

        TextView week_item_zannum=(TextView) convertView.findViewById(R.id.week_item_zannum);

        TextView week_item_comment=(TextView) convertView.findViewById(R.id.week_item_comment);

        String label_color = list.get(position).getLabel_color();

       GradientDrawable drawable=(GradientDrawable) label.getBackground();
       drawable.setColor(Color.parseColor(label_color));

        label.setText(list.get(position).getLabel_text());
        title.setText(list.get(position).getTopic().getTitle());

        ImageLoader.getInstance().displayImage(list.get(position).getCover_image_url(),imageView);

        below_title.setText(list.get(position).getTitle());

        week_item_zannum.setText(list.get(position).getLikes_count()+"");

        week_item_comment.setText(list.get(position).getComments_count()+"");

        //点赞动画
        iv_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet set=new AnimatorSet();
                set.playTogether(ObjectAnimator.ofFloat(v,"scaleY",1,1.4f,1),
                        ObjectAnimator.ofFloat(v,"scaleX",1,1.4f,1));

                set.setStartDelay(500);
                set.start();

            }
        });

        return convertView;
    }
}
