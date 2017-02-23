package com.example.rent.sdacourseapplication.todo.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rent.sdacourseapplication.R;

/**
 * Created by RENT on 2017-02-22.
 */

public class ToDoListActivity extends AppCompatActivity implements OnItemCheckStateChanged {

    private ToDOListAdapter toDOListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_activity);

        activityTitle = getSupportActionBar().getTitle().toString();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        toDOListAdapter = new ToDOListAdapter();
        recyclerView.setAdapter(toDOListAdapter);
        toDOListAdapter.setCheckListener(this);

        final EditText editText = (EditText) findViewById(R.id.todo_edit_text);

        Button addButton = (Button) findViewById(R.id.todo_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDOListAdapter.addItem(editText.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_item) {
            toDOListAdapter.deleteAllCheckedItems();
        }
        return super.onOptionsItemSelected(item);
    }

    private String activityTitle;
    @Override
    public void onItemCheckStateChanged(int chceckedItemsCount) {
        getSupportActionBar().setTitle("Checked items: "+ chceckedItemsCount);
    }
}
