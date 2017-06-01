package dongting.bwei.com.comic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import dongting.bwei.com.comic.tab_fragment.CommunityFragment;
import dongting.bwei.com.comic.tab_fragment.FindFragment;
import dongting.bwei.com.comic.tab_fragment.HomeFragment;
import dongting.bwei.com.comic.tab_fragment.MyFragment;
import dongting.bwei.com.comic.listener.InitListener;

public class MainActivity extends FragmentActivity implements InitListener{

    private List<Fragment> list =new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

       list.add(new HomeFragment());
        list.add(new FindFragment());
        list.add(new CommunityFragment());
        list.add(new MyFragment());

        init();

       showFragment(0);
    }

    @Override
    public void init() {
        RadioGroup tab_rg=(RadioGroup) findViewById(R.id.tab_rg);

        RadioButton tab_rb_home=(RadioButton)findViewById(R.id.tab_rb_home);
        RadioButton tab_rb_find=(RadioButton)findViewById(R.id.tab_rb_find);
        RadioButton tab_rb_community=(RadioButton)findViewById(R.id.tab_rb_community);
        RadioButton tab_rb_my=(RadioButton)findViewById(R.id.tab_rb_my);

        tab_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.tab_rb_home:
                        showFragment(0);
                    break;

                    case R.id.tab_rb_find:
                        showFragment(1);
                        break;

                    case R.id.tab_rb_community:
                        showFragment(2);
                        break;

                    case R.id.tab_rb_my:
                        showFragment(3);

                        break;
                }
            }
        });
    }

    private void showFragment(int index){
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        if(!list.get(index).isAdded()){
            transaction.add(R.id.frame,list.get(index));
        }
        for(int i=0;i<list.size();i++){
            if(i==index){
                transaction.show(list.get(i));
            }else{
                transaction.hide(list.get(i));
            }
        }
        transaction.commit();
    }
}
