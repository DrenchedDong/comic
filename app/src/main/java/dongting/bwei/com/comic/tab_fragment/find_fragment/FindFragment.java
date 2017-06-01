package dongting.bwei.com.comic.tab_fragment.find_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import dongting.bwei.com.comic.R;


/**
 * Created by lenovo on 2017/4/25.
 */

public class FindFragment extends Fragment{

    private RadioGroup radioGroup;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment, container, false);
        radioGroup = (RadioGroup) view.findViewById(R.id.public_title);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_find);
   viewPager.setCurrentItem(0);

       init();
  viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
     switch (position) {
         case 0:
     radioGroup.check(R.id.title_focus);
             break;

       case 1:

           radioGroup.check(R.id.title_hot);
             break;
     }


      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
  });

 radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
     @Override
     public void onCheckedChanged(RadioGroup group, int checkedId) {
         switch (checkedId) {
             case R.id.title_focus:
          viewPager.setCurrentItem(0);

                 break;

             case R.id.title_hot:
              viewPager.setCurrentItem(1);



                 break;
         }
     }
 });


        return view;
    }

    private void init() {

       viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
           @Override
           public Fragment getItem(int position) {
            Fragment fragment=null;
              switch (position) {
                  case 0:
                 fragment=new ClassifyFragment();
                      break;

                  case 1:
                  fragment=new RecommendFragment();

                      break;
              }


               return fragment;
           }

           @Override
           public int getCount() {
               return 2;
           }
       });



    }


}
