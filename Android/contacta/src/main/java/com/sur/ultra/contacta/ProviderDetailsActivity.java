package com.sur.ultra.contacta;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sur.ultra.contacta.Models.Provider;
import com.sur.ultra.contacta.Models.Providers;

import java.util.Date;

public class ProviderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ProviderDetailsActivity.this, ChatActivity.class));
                }
            });
        }

        Providers providers = new Providers();

        Provider provider = providers.getOne(getIntent().getExtras().getString("ProviderId"));

        TextView nameView = (TextView) findViewById(R.id.providerInfo);
        if (nameView != null) {
            nameView.setText(provider.getInfo());
        }

        setTitle(provider.getName());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        load();
    }

    private ListView mListView = null;
    private MessageItemAdapter mMessageItemAdapter;

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

    public void load(){
        mListView = (ListView) findViewById(R.id.listInbox);
        mMessageItemAdapter = new MessageItemAdapter(this, R.layout.messages_row, myMessagesList);

        if (mListView != null) {
            mListView.setAdapter(mMessageItemAdapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                startActivity(new Intent(ProviderDetailsActivity.this, ChatActivity.class));
            }
        });
    }

    public void confirmDisconnection(View view){

        new AlertDialog.Builder(ProviderDetailsActivity.this)
                .setTitle("Desconectar Nombre del proveedor")
                .setMessage("¿Esta seguro de que desea desconectarse del proveedor?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ProviderDetailsActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProviderDetailsActivity.this, ProvidersActivity.class));
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
}
