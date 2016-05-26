package com.sur.ultra.contacta;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.Date;

public class NewsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "NewsActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;
//    private ProgressBar mRegistrationProgressBar;
//    private TextView mInformationTextView;
    private boolean isReceiverRegistered;

    MessageListItem[] myMessagesList = new MessageListItem[]{
            new MessageListItem("Donec congue ligula vel auctor faucibus.", "Banco Mercantil", true, "avatar", new Date() ),
            new MessageListItem("Nulla auctor ante ac diam ultrices blandit in gravida arcu.", "CANTV", true, "avatar", new Date()),
            new MessageListItem("Sed at leo vehicula, rhoncus nulla vitae, dictum odio", "GMVV", true, "avatar", new Date()),
            new MessageListItem("Proin eu dui dapibus, pharetra leo a, commodo velit.", "Banco Mercantil", true, "avatar", new Date()),
            new MessageListItem("Aliquam eu nisl eu magna euismod ullamcorper.", "CANTV", true, "avatar", new Date()),
            new MessageListItem("Nam eleifend augue eget lorem dapibus tincidunt.", "Movistar de Venezuela", true, "avatar", new Date()),
            new MessageListItem("Proin sed massa a nisl pellentesque mattis.", "GMVV", false, "avatar", new Date()),
            new MessageListItem("Cras sed velit sed velit viverra mollis.", "LaIguana.TV", true, "avatar", new Date()),
            new MessageListItem("Aenean et diam dignissim, facilisis urna eget, venenatis urna.", "Banco Mercantil", true, "avatar", new Date())
    };

    private ListView mListView = null;
    private MessageItemAdapter mMessageItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        /* Comienzo copia de GCM*/
//        mRegistrationProgressBar = (ProgressBar) findViewById(R.id.registrationProgressBar);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
//                mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
//                if (sentToken) {
//                    mInformationTextView.setText(getString(R.string.gcm_send_message));
//                } else {
//                    mInformationTextView.setText(getString(R.string.token_error_message));
//                }
            }
        };
//        mInformationTextView = (TextView) findViewById(R.id.informationTextView);

        // Registering BroadcastReceiver
        registerReceiver();

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
        /* FIN copia de GCM*/


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
//                Toast.makeText(NewsActivity.this, myMessagesList[i].author, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NewsActivity.this, NewsDetailActivity.class));
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
        getMenuInflater().inflate(R.menu.inbox, menu);
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
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void registerReceiver(){
        if(!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}