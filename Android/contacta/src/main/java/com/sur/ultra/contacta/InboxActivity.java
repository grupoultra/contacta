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

import com.sur.ultra.contacta.Fragments.InboxFragment;
import com.sur.ultra.contacta.Fragments.InitializationFragment;
import com.sur.ultra.contacta.Fragments.MessagesFragment;
import com.sur.ultra.contacta.Fragments.NewsDetailFragment;
import com.sur.ultra.contacta.Fragments.ProviderDetailFragment;
import com.sur.ultra.contacta.Fragments.ProvidersFragment;
import com.sur.ultra.contacta.Fragments.SettingsFragment;

public class InboxActivity extends AppCompatActivity
        implements MessagesFragment.OnMessageSelectedListener,
                   ProvidersFragment.OnProviderSelectedListener {

    private DrawerLayout drawerLayout;
    private static final String TAG = "InboxActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
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
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {
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
                    .replace(R.id.main_container, mFragment)
                    .commit();
        }

        // Setear título actual
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
                new AlertDialog.Builder(InboxActivity.this)
                        .setTitle("Ordenar")
                        .setPositiveButton("Por proveedor", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(InboxActivity.this, "Ordenado por proveedor", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Por fecha", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(InboxActivity.this, "Ordenado por fecha", Toast.LENGTH_SHORT).show();
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
            Fragment mFragment = null;
            FragmentManager fragmentManager = getSupportFragmentManager();
            mFragment = new NewsDetailFragment();

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, mFragment)
                    .addToBackStack( "tag" )
                    .commit();
        }
    }

    @Override
    public void onProviderSelected(int position) {
        Fragment mFragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragment = new ProviderDetailFragment();

        fragmentManager
                .beginTransaction()
                .replace(R.id.main_container, mFragment)
                .addToBackStack( "tag" )
                .commit();
    }
}
