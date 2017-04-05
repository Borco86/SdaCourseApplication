package com.example.rent.sdacourseapplication.todo.list;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rent.sdacourseapplication.R;

import java.util.ArrayList;

/**
 * Created by RENT on 2017-02-22.
 */

public class ToDoListActivity extends AppCompatActivity implements OnItemCheckStateChanged {

    private static final String ADAPTER_DATA = "";
    private ToDOListAdapter toDOListAdapter;
    private ActionMode actionMode;

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
                editText.setText("");
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

        return super.onOptionsItemSelected(item);
    }

    private String activityTitle;

    @Override
    public void onItemCheckStateChanged(int chceckedItemsCount) {
        if (chceckedItemsCount > 0) {
            if (actionMode == null) {
                creaActionMode();
            }
            //actionMode.setTitle(getString(R.string.chcecked_items, chceckedItemsCount));
            actionMode.setTitle(getResources().getQuantityString(R.plurals.chcecked_items_plural, chceckedItemsCount, chceckedItemsCount));

        } else {
            if (actionMode != null) {
                actionMode.finish();
            }
            getSupportActionBar().setTitle(activityTitle);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(ADAPTER_DATA, new ArrayList<>(toDOListAdapter.getItems()));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        toDOListAdapter.setItems(savedInstanceState.<ToDoListItem>getParcelableArrayList(ADAPTER_DATA));
    }

    private void creaActionMode() {
        actionMode = startSupportActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                mode.getMenuInflater().inflate(R.menu.todo_action_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {
                    toDOListAdapter.deleteAllCheckedItems();
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

                actionMode = null;
                toDOListAdapter.deselectAllItems();

            }
        });
    }
}
