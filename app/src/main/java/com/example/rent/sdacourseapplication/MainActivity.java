package com.example.rent.sdacourseapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rent.sdacourseapplication.MVP.MvpActivity;
import com.example.rent.sdacourseapplication.books.BooksActivity;
import com.example.rent.sdacourseapplication.drawing.app.DrawingActivity;
import com.example.rent.sdacourseapplication.milionaires.quiz.MillionairesActivity;
import com.example.rent.sdacourseapplication.prophecy.ProphecyActivity;
import com.example.rent.sdacourseapplication.todo.list.ToDoListActivity;

public class MainActivity extends AppCompatActivity {

    public static final String NOTES_KEY = "notes";//przechowuje tekst
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        TextView textView = (TextView) findViewById(R.id.drawing_app);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DrawingActivity.class);
                startActivity(intent);
            }
        });

        TextView toDoList = (TextView) findViewById(R.id.to_do_text_view);
        toDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ToDoListActivity.class);
                startActivity(intent);
            }
        });

        TextView milionaires = (TextView) findViewById(R.id.milionaires_view);
        milionaires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MillionairesActivity.class);
                startActivity(intent);
            }
        });

        TextView booksList = (TextView) findViewById(R.id.books_view);
        booksList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BooksActivity.class);
                startActivity(intent);
            }
        });

        TextView prophecy = (TextView) findViewById(R.id.prophecy_view);
        prophecy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProphecyActivity.class);
                startActivity(intent);
            }
        });
        TextView mvp = (TextView) findViewById(R.id.mvp_view);
        mvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MvpActivity.class);
                startActivity(intent);
            }
        });


        final EditText noteEditText = (EditText) findViewById(R.id.my_note_edit_text);
        // wczytywanie po onResume()
        noteEditText.setText(readText());

        Button saveButton = (Button) findViewById(R.id.save_note);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText(noteEditText.getText().toString());
            }
        });

    }

    //czytanie
    private String readText() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getString(NOTES_KEY, "");
    }

    //zapisywanie wpisywanego tekstu
    private void saveText(String text) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putString(NOTES_KEY, text).apply();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);
                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }
//                Toast.makeText(DrawingActivity.this, "Tekścik", Toast.LENGTH_SHORT).show();
//                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
