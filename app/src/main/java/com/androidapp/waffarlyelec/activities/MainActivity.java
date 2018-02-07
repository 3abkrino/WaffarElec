package com.androidapp.waffarlyelec.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.androidapp.waffarlyelec.R;
import com.androidapp.waffarlyelec.fragments.MainFragment;
import com.androidapp.waffarlyelec.fragments.SettingsFragment;
import com.androidapp.waffarlyelec.views.CustomTextView;
import com.androidapp.waffarlyelec.views.CustomTypefaceSpan;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment;
    private Toolbar toolbar;
    private CustomTextView titleToolbarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titleToolbarTextView = findViewById(R.id.toolbar_title_text_view);
        titleToolbarTextView.setText(R.string.home_page);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // -----------------------------------------------------------------------------------------
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu m = navigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            applyFontToMenuItem(mi);
        }

        navigationView.setNavigationItemSelectedListener(this);

        // -----------------------------------------------------------------------------------------
        // Set Default Fragment
        fragment = new MainFragment();
        setFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragment = new MainFragment();
            titleToolbarTextView.setText(R.string.home_page);
        } else if (id == R.id.nav_waffarly_alert) {

        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_consumption_past) {

        } else if (id == R.id.nav_consumption_now) {

        } else if (id == R.id.nav_advice) {

        } else if (id == R.id.nav_ads) {

        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();
            titleToolbarTextView.setText(R.string.settings);
        } else if (id == R.id.nav_logout) {

        }

        if (fragment != null) {
            setFragment(fragment);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, fragment);
        fragmentTransaction.commit();
    }

    // Change Fonts of Navigation Drawer
    private void applyFontToMenuItem(MenuItem mi) {
        final String FONT_NAME = "jf_flat_regular.ttf";
        Typeface font = Typeface.createFromAsset(getAssets(), FONT_NAME);
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
}
