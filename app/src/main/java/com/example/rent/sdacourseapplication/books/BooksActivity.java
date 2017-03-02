package com.example.rent.sdacourseapplication.books;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rent.sdacourseapplication.R;

import java.util.Arrays;
import java.util.List;

public class BooksActivity extends AppCompatActivity {

    private BooksPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        viewPager = (ViewPager) findViewById(R.id.books_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        Book book = new Book(1, R.drawable.effective_java, "Effective");
        book.setRead(sharedPreferences.getBoolean(String.valueOf(book.getId()),false));
        Book book2 = new Book(2, R.drawable.clean_code_, "Clean Code");
        book2.setRead(sharedPreferences.getBoolean(String.valueOf(book2.getId()),false));
        Book book3 = new Book(3, R.drawable.head_first_drsign_patterns, "Head first");
        book3.setRead(sharedPreferences.getBoolean(String.valueOf(book3.getId()),false));
        List<Book> list = Arrays.asList(book, book2, book3);

        pagerAdapter = new BooksPagerAdapter(list, sharedPreferences);
        viewPager.setAdapter(pagerAdapter);
    }
}
