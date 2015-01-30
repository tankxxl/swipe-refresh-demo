package com.umpay.swipetorefreshdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mListView = (ListView) findViewById(R.id.activity_main_listview);
        mNames = Arrays.asList(getResources().getStringArray(R.array.names));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mNames);
        mListView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                                getNewNames());
                        mListView.setAdapter(mAdapter);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });
    }

    private List<String> getNewNames() {

        List<String> newNames = new ArrayList<String>();
        for (int i = 0; i < mNames.size(); i++) {
            int randomNameIndex = new Random().nextInt(mNames.size() - 1);
            newNames.add(mNames.get(randomNameIndex));
        }
        return newNames;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
