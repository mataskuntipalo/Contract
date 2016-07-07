package com.example.intern.intern;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class viewContact extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        TextView name = (TextView)findViewById(R.id.name);
        ImageView back = (ImageView)findViewById(R.id.backArrow);



        String id = getIntent().getExtras().getString("id");
        name.setText(id);

    }


    public void backPage(View v) {
        finish();
    }
}
