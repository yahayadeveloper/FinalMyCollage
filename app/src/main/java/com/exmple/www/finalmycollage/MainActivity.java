package com.exmple.www.finalmycollage;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    /* renamed from: com.wisleem.mycollage.MainActivity.1 */
    class C02051 implements OnClickListener {
        final /* synthetic */ FloatingActionButton val$fab;

        C02051(FloatingActionButton floatingActionButton) {
            this.val$fab = floatingActionButton;
        }

        public void onClick(View view) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, AddActivity.class), ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, this.val$fab, MainActivity.this.getString(R.string.app_name)).toBundle());
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(5);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new C02051(fab));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
