package com.sur.ultra.contacta;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.sur.ultra.contacta.Fragments.InboxFragment;
import com.sur.ultra.contacta.Fragments.InitializationFragment;
import com.sur.ultra.contacta.Fragments.MessagesFragment;
import com.sur.ultra.contacta.Fragments.NewsDetailFragment;
import com.sur.ultra.contacta.Fragments.ProviderDetailFragment;
import com.sur.ultra.contacta.Fragments.ProvidersFragment;
import com.sur.ultra.contacta.Fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity
        implements MessagesFragment.OnMessageSelectedListener,
                   ProvidersFragment.OnProviderSelectedListener {

    private DrawerLayout drawerLayout;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            prepareDrawer(navigationView);
            // Seleccionar item por defecto
            if (savedInstanceState == null) {
                selectItem(navigationView.getMenu().getItem(0));
            }
        }
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

    }

    private void prepareDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        selectItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    private void selectItem(MenuItem itemDrawer) {
        Fragment mFragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            case R.id.nav_inbox:
                mFragment = new InboxFragment();
                break;
            case R.id.nav_providers:
                mFragment = new ProvidersFragment();
                break;
            case R.id.nav_settings:
                mFragment = new SettingsFragment();
                break;
            case R.id.nav_initialization:
                mFragment = new InitializationFragment();
                break;
        }
        if (mFragment != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, mFragment, "ActualFragment")
                    .commit();
        }

        setTitle(itemDrawer.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_sort:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Ordenar")
                        .setPositiveButton("Por proveedor", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Ordenado por proveedor", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Por fecha", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Ordenado por fecha", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMessageSelected(int id, String type) {
        if (type.equals("message")){
            startActivity(new Intent(this, ChatActivity.class));
        } else{
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment mFragment = NewsDetailFragment.newInstance(id);

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, mFragment)
                    .addToBackStack( "tag" )
                    .commit();
        }
    }

    @Override
    public void onProviderSelected(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment mFragment = ProviderDetailFragment.newInstance(id);

        fragmentManager
                .beginTransaction()
                .replace(R.id.main_container, mFragment)
                .addToBackStack( "tag" )
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
