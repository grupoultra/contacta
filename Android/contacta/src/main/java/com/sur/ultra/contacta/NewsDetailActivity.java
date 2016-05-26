package com.sur.ultra.contacta;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView nameView = (TextView) findViewById(R.id.newsBody);
        nameView.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed congue justo, vel pulvinar nunc. Morbi ultricies porta dolor a auctor. Mauris vel lorem imperdiet, sagittis leo at, faucibus justo. Vestibulum id lacus lorem. Proin ipsum sem, dignissim vel luctus porttitor, condimentum vel purus. Integer id efficitur arcu, quis venenatis neque. Proin metus dui, mattis ac ullamcorper ac, vehicula ut metus. Aenean mattis, nunc malesuada suscipit ultrices, leo diam porta nulla, quis iaculis elit augue vel odio. Sed dapibus augue et mi vulputate, auctor malesuada dui suscipit. Integer id urna fermentum, sollicitudin orci a, eleifend est. Quisque suscipit eget velit eget hendrerit. Fusce mi leo, tempus porta eleifend sed, aliquet sit amet eros. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Phasellus et dolor lacus. Integer hendrerit, tellus ac posuere congue, augue augue ultrices risus, quis convallis justo diam et urna.\n" +
                "\n" +
                "Maecenas congue, odio sed sodales dictum, diam risus efficitur nisl, ac venenatis nisl arcu et dolor. Etiam vel tristique turpis. Pellentesque lectus felis, porttitor at porttitor ut, cursus id mauris. Vivamus elit augue, porttitor vel vulputate nec, varius feugiat est. Vivamus et egestas diam, vel sollicitudin lorem. Vivamus diam libero, egestas et semper quis, iaculis sit amet enim. In hac habitasse platea dictumst. Proin pretium lacus ac ullamcorper finibus.");
    }

    public void likeNews(View view){
        Toast.makeText(NewsDetailActivity.this, "La noticia habla bien del tema", Toast.LENGTH_SHORT).show();
    }
    public void dislikeNews(View view){
        Toast.makeText(NewsDetailActivity.this, "La noticia habla mal del tema", Toast.LENGTH_SHORT).show();
    }
    public void moreInformation(View view){
        Toast.makeText(NewsDetailActivity.this, "Link externo de la noticia", Toast.LENGTH_SHORT).show();
    }

}
