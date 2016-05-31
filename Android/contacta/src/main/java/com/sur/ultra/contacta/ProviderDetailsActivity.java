package com.sur.ultra.contacta;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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


        TextView nameView = (TextView) findViewById(R.id.providerInfo);
        nameView.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed congue justo, vel pulvinar nunc. Morbi ultricies porta dolor a auctor. Mauris vel lorem imperdiet, sagittis leo at, faucibus justo. Vestibulum id lacus lorem. Proin ipsum sem, dignissim vel luctus porttitor, condimentum vel purus. Integer id efficitur arcu, quis venenatis neque. Proin metus dui, mattis ac ullamcorper ac, vehicula ut metus. Aenean mattis, nunc malesuada suscipit ultrices, leo diam porta nulla, quis iaculis elit augue vel odio. Sed dapibus augue et mi vulputate, auctor malesuada dui suscipit. Integer id urna fermentum, sollicitudin orci a, eleifend est. Quisque suscipit eget velit eget hendrerit. Fusce mi leo, tempus porta eleifend sed, aliquet sit amet eros. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Phasellus et dolor lacus. Integer hendrerit, tellus ac posuere congue, augue augue ultrices risus, quis convallis justo diam et urna.\n" +
                "\n" +
                "Maecenas congue, odio sed sodales dictum, diam risus efficitur nisl, ac venenatis nisl arcu et dolor. Etiam vel tristique turpis. Pellentesque lectus felis, porttitor at porttitor ut, cursus id mauris. Vivamus elit augue, porttitor vel vulputate nec, varius feugiat est. Vivamus et egestas diam, vel sollicitudin lorem. Vivamus diam libero, egestas et semper quis, iaculis sit amet enim. In hac habitasse platea dictumst. Proin pretium lacus ac ullamcorper finibus.");

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
