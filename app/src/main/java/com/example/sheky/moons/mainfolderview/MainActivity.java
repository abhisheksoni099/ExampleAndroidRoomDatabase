package com.example.sheky.moons.mainfolderview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sheky.moons.R;
import com.example.sheky.moons.itemadditionview.ItemAdditionFragment;
import com.example.sheky.moons.itemsview.ItemsFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment fragmentMain;
    private boolean notOnMainPage;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        fragmentMain = getSupportFragmentManager().findFragmentById(R.id.fragmentMain);
        setSupportActionBar(toolbar);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openItemAdditionPage();
            }
        });
    }

    private void openItemAdditionPage() {
        floatingActionButton.setVisibility(View.GONE);
        notOnMainPage = true;
        fragmentMain.getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentMain, new ItemAdditionFragment(), "")
                .commit();
    }

    public void openFolder(int folderId) {
        notOnMainPage = true;
        ItemsFragment itemsFragment = new ItemsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("folderid", folderId);
        itemsFragment.setArguments(bundle);
        fragmentMain.getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentMain, itemsFragment, "")
                .commit();
    }

    @Override
    public void onBackPressed() {
        floatingActionButton.setVisibility(View.VISIBLE);
        if(notOnMainPage) {
            fragmentMain.getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentMain, new FoldersFragment(), "")
                    .commit();
            notOnMainPage = false;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings) {
            return true;
        } else if(id == R.id.action_sort) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
