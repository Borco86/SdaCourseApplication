package com.example.rent.sdacourseapplication.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rent.sdacourseapplication.R;
import com.example.rent.sdacourseapplication.drawing.app.DrawingActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GalleryActivity extends AppCompatActivity {

    private DrawingPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        File dir = getExternalFilesDir(DrawingActivity.DRAWING_GALLERY_DIR);
        File[] files = dir.listFiles();
        pagerAdapter = new DrawingPagerAdapter(files);
        viewPager.setAdapter(pagerAdapter);


//        File dir = getExternalFilesDir(DrawingActivity.DRAWING_GALLERY_DIR);
//        File[] files = dir.listFiles();
//        File file = files[0];
//        FileInputStream fileInputStream = null;
//        try {
//            fileInputStream = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        BitmapFactory.decodeStream(fileInputStream);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gallery_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            pagerAdapter.deleteItem(viewPager.getCurrentItem());
        }
        return super.onOptionsItemSelected(item);
    }
}
