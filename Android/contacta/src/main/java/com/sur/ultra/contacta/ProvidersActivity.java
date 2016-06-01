package com.sur.ultra.contacta;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.sur.ultra.contacta.Models.Providers;

/**
 * Created by alexis on 5/23/16.
 */
public class ProvidersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CProvider[] myCProviderList = new CProvider[]{
            new CProvider("Banco Mercantil", true, "avatar"),
            new CProvider("Cantv", true, "avatar"),
            new CProvider("Movistar de Venezuela", true, "avatar"),
            new CProvider("GMVV", true, "avatar"),
            new CProvider("LaIguana.TV", true, "avatar"),
            new CProvider("Banco Provincial", true, "avatar"),
    };

    Providers providers = new Providers();

    private ListView mListView = null;
    private CProviderAdapter mCProviderAdapter;

    private static final String TAG = "ProvidersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_providers);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ProvidersActivity.this, SearchProvidersActivity.class));
//            }
//        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        load();
    }

    public void load(){
        mListView = (ListView) findViewById(R.id.listProviders);
        mCProviderAdapter = new CProviderAdapter(this, R.layout.providers_row, providers.getAll());

        if (mListView != null) {
            mListView.setAdapter(mCProviderAdapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
//                Toast.makeText(ProvidersActivity.this, myMessagesList[i].author, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProvidersActivity.this, ProviderDetailsActivity.class));
            }
        });
    }

    public void confirmDisconnection(View view){

        new AlertDialog.Builder(ProvidersActivity.this)
                .setTitle("Desconectar Nombre del proveedor")
                .setMessage("¿Esta seguro de que desea desconectarse del proveedor?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ProvidersActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProvidersActivity.this, ProvidersActivity.class));
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ProvidersActivity.this, "No", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.providers, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            searchView.setOnQueryTextListener(
                    new SearchView.OnQueryTextListener() {

                        @Override
                        public boolean onQueryTextSubmit(String s) {
                            Toast.makeText(ProvidersActivity.this, s, Toast.LENGTH_SHORT).show();

                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String s) {
                            Log.d(TAG, "Query Text Change");
                            return false;
                        }
                    });
        }

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_providers) {
            startActivity(new Intent(this, ProvidersActivity.class));
        } else if (id == R.id.nav_news) {
            startActivity(new Intent(this, NewsActivity.class));
        } else if (id == R.id.nav_messages) {
            startActivity(new Intent(this, MessagesActivity.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.nav_initialization) {
            startActivity(new Intent(this, InitializationActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
