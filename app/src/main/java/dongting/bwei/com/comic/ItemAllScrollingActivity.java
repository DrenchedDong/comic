package dongting.bwei.com.comic;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class ItemAllScrollingActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_all_scrolling);
        imageView = (ImageView) findViewById(R.id.image_item_scroll);
        Intent intent = getIntent();
          String path= intent.getStringExtra("scroll");
        ImageLoader.getInstance().displayImage(path,imageView);



    }
}
