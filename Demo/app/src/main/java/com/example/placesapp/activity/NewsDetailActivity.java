package com.example.placesapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.placesapp.R;

import org.w3c.dom.Text;

public class NewsDetailActivity extends BaseActivity {

    private ImageView imageView;

    //the images to display
    Integer[] imageIDs = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image1,
            R.drawable.image2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = (ImageView) findViewById(R.id.image1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                Intent intent = new Intent("android.intent.action.MAPS");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Global.CurentContext.startActivity(intent);
            }
        });
        Global.CurentContext = this;

        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position,long id)
            {
                //Toast.makeText(getBaseContext(), "pic" + (position + 1) + " selected",
                        //Toast.LENGTH_SHORT).show();
                // display the images selected
                imageView.setImageResource(imageIDs[position]);
            }
        });

        getNewsDetailData();
    }

    private void getNewsDetailData(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String titleStr = extras.getString("title");
            String usernameStr = extras.getString("username");
            TextView username = (TextView)findViewById(R.id.username);
            username.setText(usernameStr);
            TextView title = (TextView) findViewById(R.id.title);
            title.setText(titleStr);
            ImageView avatar = (ImageView)findViewById(R.id.avatar);
            avatar.setImageResource(R.drawable.image1);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override protected int getLayoutResource() {
        return R.layout.activity_detail_news;
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private int itemBackground;
        public ImageAdapter(Context c)
        {
            context = c;
            // sets a grey background; wraps around the images
            TypedArray a =obtainStyledAttributes(R.styleable.MyGallery);
            itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            a.recycle();
        }
        // returns the number of images
        public int getCount() {
            return imageIDs.length;
        }
        // returns the ID of an item
        public Object getItem(int position) {
            return position;
        }
        // returns the ID of an item
        public long getItemId(int position) {
            return position;
        }
        // returns an ImageView view
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imageIDs[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(100, 100));
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }

}



