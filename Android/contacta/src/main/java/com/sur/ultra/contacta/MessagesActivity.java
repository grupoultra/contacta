package com.sur.ultra.contacta;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;
import java.util.Date;

public class MessagesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MessagesActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private boolean isReceiverRegistered;

    MessageListItem[] myMessagesList = new MessageListItem[]{
            new MessageListItem("Donec congue ligula vel auctor faucibus.", "Banco Mercantil", true, "avatar", new Date() ),
            new MessageListItem("Aenean et diam dignissim, facilisis urna eget, venenatis urna.", "Banco Mercantil", true, "avatar", new Date()),
            new MessageListItem("Sed at leo vehicula, rhoncus nulla vitae, dictum odio", "GMVV", true, "avatar", new Date()),
            new MessageListItem("Aliquam eu nisl eu magna euismod ullamcorper.", "CANTV", true, "avatar", new Date()),
            new MessageListItem("Nam eleifend augue eget lorem dapibus tincidunt.", "Movistar de Venezuela", true, "avatar", new Date()),
            new MessageListItem("Proin sed massa a nisl pellentesque mattis.", "GMVV", true, "avatar", new Date()),
            new MessageListItem("Cras sed velit sed velit viverra mollis.", "LaIguana.TV", true, "avatar", new Date()),
            new MessageListItem("Nulla auctor ante ac diam ultrices blandit in gravida arcu.", "CANTV", true, "avatar", new Date())
    };

    private ListView mListView = null;
    private MessageItemAdapter mMessageItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        mListView = (ListView) findViewById(R.id.listInbox);
        mMessageItemAdapter = new MessageItemAdapter(this, R.layout.messages_row, myMessagesList);

        if (mListView != null) {
            mListView.setAdapter(mMessageItemAdapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                startActivity(new Intent(MessagesActivity.this, ChatActivity.class));
            }
        });
    }


//    public void load(){
//        BaseHelper baseHelper = new BaseHelper(this, "ContactaDB", null, 1);
//
//        SQLiteDatabase db = baseHelper.getReadableDatabase();
//
//        if(db!=null){
//            Cursor c = db.rawQuery("SELECT * from Mensajes", null);
//
//            String [] arreglo = new String[c.getCount()];
//            int i = 0;
//
//            if (c.moveToFirst()){
//                do {
//                    String linea = c.getInt(0)+ " " + c.getString(1);
//
//                    arreglo[i] = linea;
//                    i++;
//
//                } while (c.moveToNext());
//            }
//
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arreglo);
//            ListView lista = (ListView) findViewById(R.id.listInbox);
//
//            lista.setAdapter(adapter);
//        }
//    }

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
        getMenuInflater().inflate(R.menu.messages, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort) {
            final ArrayList mSelectedItems = new ArrayList();  // Where we track the selected items

            new AlertDialog.Builder(MessagesActivity.this)
                    .setTitle("Ordenar")
                    .setPositiveButton("Por proveedor", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MessagesActivity.this, "Ordenado por proveedor", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Por fecha", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MessagesActivity.this, "Ordenado por fecha", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
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
