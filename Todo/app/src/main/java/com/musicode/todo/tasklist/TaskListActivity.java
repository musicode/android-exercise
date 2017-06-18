package com.musicode.todo.tasklist;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.musicode.todo.R;

public class TaskListActivity extends AppCompatActivity {

    private ActionBar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mToolbar = getSupportActionBar();
        mToolbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mToolbar.setDisplayHomeAsUpEnabled(true);

        mToolbar.setTitle("hahah哈");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tasklist_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_filter:
                showFilteringPopupMenu();
                break;
            case R.id.menu_clear:
                toast("menu_clear");
                break;
            case R.id.menu_refresh:
                toast("menu_refresh");
                break;
        }
        return true;
    }

    private void showFilteringPopupMenu() {
        PopupMenu popup = new PopupMenu(this, findViewById(R.id.menu_filter));
        popup.getMenuInflater().inflate(R.menu.filter_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.filter_all:
                        toast("filter_all");
                        break;
                    case R.id.filter_active:
                        toast("filter_active");
                        break;
                    case R.id.filter_completed:
                        toast("filter_completed");
                        break;
                }
                return true;
            }
        });
        popup.show();
    }

    private void toast(String title) {
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }
}
