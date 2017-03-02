package com.example.rent.sdacourseapplication.books;

import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.rent.sdacourseapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-03-02.
 */

public class BooksPagerAdapter extends PagerAdapter {

    private List<Book> bookList = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    public BooksPagerAdapter(List<Book> bookList, SharedPreferences sharedPreferences) {
        this.bookList = bookList;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View pageLayout = inflater.inflate(R.layout.single_book_layout, container, false);
        ImageView image = (ImageView) pageLayout.findViewById(R.id.single_book_image);
        image.setImageResource(bookList.get(position).getImgResourceId());

        CheckBox checkBox = (CheckBox) pageLayout.findViewById(R.id.checkbox_isRead);
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(bookList.get(position).isRead());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            private Book book;

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    bookList.get(position).setRead(true);
                book = bookList.get(position);
                sharedPreferences
                        .edit()
                        .putBoolean(String.valueOf(book.getId()), book.isRead())
                        .apply();
            }
        });

        container.addView(pageLayout);
        return pageLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return bookList.get(position).getTitle();
    }
}
